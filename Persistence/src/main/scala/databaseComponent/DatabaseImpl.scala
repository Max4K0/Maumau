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

class DatabaseImpl @Inject () extends DatabaseInterface {

  val connectIP = "172.19.0.1"
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
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
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

  def writeCardList(deckId: Int, cards: List[String]): List[Int] = {
    val ids = ListBuffer[Int]()
    for (card <- cards){
      val query : DBIO[Int] = sqlu"""INSERT INTO "CARDS" VALUES ($deckId, $card)"""
      ids += Await.result(database.run(query), atMost = 10.second)
    }
    ids.toList
  }
  def readCardList(id: Int): List[String] = {
    List[String]()
  }

  def writeDeck(deck: List[String]): Int = {
    val deckIdQuery : DBIOAction[Seq[Int], slick.dbio.NoStream, Nothing] = deckTable.sortBy(_.id.desc).take(1).result
    val deckId : Int = Await.result[Seq[Int]](database.run(deckIdQuery), atMost = 10.second).head + 1
    val cardListId : List[Int] = writeCardList(deckId+1, deck)
    val query : DBIO[Int] = sqlu"""INSERT INTO "DECKS" VALUES ($deckId)"""
    Await.result[Seq[Int]](database.run(deckIdQuery), atMost = 10.second).head
  }
  def readDeck(id: Int): List[String] = {
    List[String]()
  }

  def writeDeckList(decks: List[String]): List[Int]  = {
    List[Int]()
  }
  def readDeckList(id: Int): List[List[String]] = {
    List[List[String]]()
  }

  def writePlayer(name: String, deck: String): String = {
    val deckId : Int = writeDeck(Json.parse(deck).as[List[String]])
    val query : DBIO[Int] = sqlu"""INSERT INTO "PLAYERS" VALUES ($name, $deckId);"""
    Await.result[Int](database.run(query), atMost = 10.second)
    name
  }
  def readPlayer(name: String): String = {
    "test"
  }

  def writePlayerList(players: List[String]): List[String] = {
    val ids = ListBuffer[String]()
    for (player <- players) {
      val playerJson: JsValue = Json.parse(player)
      val playerName: String = (playerJson \ "name").get.toString
      val playerDeck: String = (playerJson \ "playerDeck").get.as[String]
      ids += writePlayer(playerName, playerDeck)
    }
    ids.toList
  }
  def readPlayerList(name: String): List[String] = {
    List[String]()
  }

  def writeTable(table: String): Unit = {
    val tableJson: JsValue = Json.parse(table)
    val players: List[String] = writePlayerList((tableJson \ "player").get.as[List[String]])
    val deckIds: List[Int] = writeDeckList((tableJson \ "tableDecks").get.as[List[String]])

    val query : DBIO[Int] = sqlu"""INSERT INTO "GAME_TABLE" VALUES (NULL, '${players.head}', '${players.last}', '${deckIds.head}', '${deckIds.last}');"""
    Await.result(database.run(query), Duration.Inf)
  }
  def readTable(): String = {
    "test"
  }

  override def printDB(): Unit = {
    val query = sql"""SELECT * FROM "CARDS"""".as[(Int, String)]
    val res = Await.result[Seq[(Int,String)]](database.run(query), Duration.Inf)
    println(res)
  }
}
