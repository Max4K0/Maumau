package databaseComponent

import databaseComponent.DatabaseInterface
import play.api.libs.json.{JsArray, JsValue, Json}
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*

import com.google.inject.Inject

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import databaseComponent.Slick.tables.{CardTable, DeckTable, GameTable, PlayerTable}
import io.circe.syntax._
import io.circe.Json as CirceJson


class DatabaseImpl @Inject () extends DatabaseInterface {

  val connectIP = "localhost"
  val connectPort = 5432
  val database_user = "postgres"
  val database_pw = "postgres"
  val database_name = "maumau"
  val deckTable = TableQuery[DeckTable]
  val gameTable = TableQuery[GameTable]
  val playerTable = TableQuery[PlayerTable]
  val cardTable = TableQuery[CardTable]

  val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name,
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver"
    )

  override def createDB(): Unit = {
    val playerDB = Future(Await.result(database.run(playerTable.schema.createIfNotExists), atMost = 100.second))
    val gameDB = Future(Await.result(database.run(gameTable.schema.createIfNotExists), atMost = 100.second))
    val deckDB = Future(Await.result(database.run(deckTable.schema.createIfNotExists), atMost = 100.second))
    val cardDB = Future(Await.result(database.run(cardTable.schema.createIfNotExists), atMost = 100.second))
    playerDB.onComplete {
      case Success(_) => println("Creation of playerTable successful!")
      case Failure(e) => println("Error: " + e)
    }
    gameDB.onComplete {
      case Success(_) => println("Creation of gameTable successful!")
      case Failure(e) => println("Error: " + e)
    }
    deckDB.onComplete {
      case Success(_) => println("Creation of deckTable successful!")
      case Failure(e) => println("Error: " + e)
    }
    cardDB.onComplete {
      case Success(_) => println("Creation of cardTable successful!")
      case Failure(e) => println("Error: " + e)
    }
  }

  def writeCardList(gameId: Int, deckId: Int, cards: List[String]): List[Int] = {
    val ids = ListBuffer[Int]()
    for (card <- cards){
      val query : DBIO[Int] = sqlu"""insert into "cards" values ($gameId, $deckId, $card);"""
      Try({
        ids += Await.result(database.run(query), atMost = 10.second)
      }) match {
        case Success(_) =>
        case Failure(exception) => throw exception
      }
    }
    ids.toList
  }
  def readCardList(deckId: Int, gameId: Int): CirceJson = {
    val action = cardTable.filter(_.deckId === deckId).filter(_.gameId === gameId).map(_.name).result
    val result = Await.result[Seq[String]](database.run(action), atMost=10.second)
    result.toList.asJson
  }

  def writeDeck(gameId: Int, deck: List[String]): Int = {
    val query : DBIO[Int] = sqlu"""insert into "decks" ("gameid") values ($gameId);"""
    Await.result[Int](database.run(query), atMost = 10.second)
    val action = deckTable.map(_.id).max.result
    val deckId = Await.result(database.run(action), atMost=10.second) match
      case Some(a) => a
      case None => throw Exception("500")
    writeCardList(gameId, deckId, deck)
    deckId
  }

  def writeDeckList(gameId: Int, decks: List[String]): List[Int]  = {
    val ids = ListBuffer[Int]()
    for (deck <- decks) {
      ids += writeDeck(gameId, Json.parse(deck).as[List[String]])
    }
    ids.toList
  }
  def readDeckList(gameId: Int): CirceJson = {
    val action = deckTable.filter(_.gameId === gameId).map(_.id).result
    val result = Await.result[Seq[Int]](database.run(action), atMost = 10.second).toList
    result.map(a => readCardList(a, gameId)).asJson
  }

  def writePlayer(gameId: Int, name: String, deck: List[String]): String = {
    val deckId : Int = writeDeck(gameId, deck)
    val query : DBIO[Int] = sqlu"""insert into "players" values ($gameId, $name, $deckId);"""
    Try({
      Await.result[Int](database.run(query), atMost = 10.second)
    }) match {
      case Success(_) =>
      case Failure(exception) => throw exception
    }
    name
  }
  def readPlayer(gameId: Int, name: String): CirceJson = {
    val action = playerTable.filter(_.gameId === gameId).filter(_.name === name).map(_.deck).take(1).result
    val result = Await.result[Seq[Int]](database.run(action), atMost = 10.second).toList.head
    Map[String, CirceJson](
      "name" -> name.asJson,
      "playerDeck" -> readCardList(result, gameId)
    ).asJson
  }

  def writePlayerList(gameId: Int, players: List[String]): List[String] = {
    val ids = ListBuffer[String]()
    for (player <- players) {
      val playerJson: JsValue = Json.parse(player)
      val playerName: String = (playerJson \ "name").get.toString
      val playerDeck: List[String] = (playerJson \ "playerDeck").as[List[String]]
      ids += writePlayer(gameId, playerName, playerDeck)
    }
    ids.toList
  }
  def readPlayerList(gameId: Int): CirceJson = {
    val action = playerTable.filter(_.gameId === gameId).map(_.name).result
    val result = Await.result[Seq[String]](database.run(action), atMost = 10.second).toList
    result.map(playerName => readPlayer(gameId, playerName)).asJson
  }

  def writeTable(table: String): Unit = {
    val tableJson: JsValue = Json.parse(table)
    val action = (gameTable.map(g => (g.playerOne, g.playerTwo, g.deckOne, g.deckTwo)) returning gameTable.map(_.id)) += (None, None, None, None)
    //val query: DBIO[Int] = sqlu"""insert into "game_table" ("playerone", "playertwo", "deckone", "decktwo") values (NULL, NULL, NULL, NULL);"""
    //Await.result(database.run(query), atMost = 100.second)
    //val action = gameTable.sortBy(_.id.desc).take(1).map(_.id).result.head
    val gameId = Await.result(database.run(action), atMost = 10.second)
    val players: List[String] = writePlayerList(gameId, (tableJson \ "player").get.toString
      .replace("},{", "}-{")
      .replace("[{", "{")
      .replace("}]", "}")
      .split("-").toList)
    val deckIds: List[Int] = writeDeckList(gameId, (tableJson \ "tableDecks").get.toString
      .replace("],[", "]-[")
      .replace("[[", "[")
      .replace("]]", "]")
      .split("-").toList)
    val updateQuery: DBIO[Int] = sqlu"""UPDATE "game_table" SET "playerone"=${players.head}, "playertwo"=${players.last}, "deckone"=${deckIds.head}, "decktwo"=${deckIds.last} WHERE "id"=$gameId;"""
    Await.result(database.run(updateQuery), atMost = 100.second)

  }
  def readTable(): String = {
    val action = gameTable.map(_.id).max.result
    val gameId = Await.result(database.run(action), atMost=10.second) match
      case Some(a) => a
      case None => throw Exception("500")
    Map[String, CirceJson](
      "player" -> readPlayerList(gameId),
      "tableDecks" -> readDeckList(gameId)
    ).asJson.toString()
  }

  override def printDB(): Unit = {
    val query = sql"""select * from "cards"""".as[(Int, String)]
    val res = Await.result[Seq[(Int,String)]](database.run(query), atMost = 100.second)
    println(res)
  }
}
