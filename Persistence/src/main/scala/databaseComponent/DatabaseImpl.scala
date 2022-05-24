package databaseComponent

import com.google.inject.Inject
import databaseComponent.DatabaseInterface
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*
import play.api.libs.json.{JsArray, JsValue, Json}

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

class DatabaseImpl @Inject () extends DatabaseInterface {

  val connectIP = "localhost"
  val connectPort = 5432
  val database_user = "postgres"
  val database_pw = "postgres"
  val database_name = "maumau"

  val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver"
    )

  def writeCardList(deckId: Int, cards: List[String]): List[Int] = {
    for (card <- cards){
      val query = sqlu"""INSERT INTO CARDS VALUES ($deckId, $card)"""
      Await.result(database.run(query), atMost = 10.second)
    }
  }
  def readCardList(id: Int): List[String]

  def writeDeck(deck: List[String]): Int = {
    val deckIdQuery = sql"""SELECT MAX(id) FROM DECKS""".as[Int]
    val deckId : Int = Await.result[Int](database.run(deckIdQuery), atMost = 10.second)
    val cardListId : Int = writeCardList(deckId, deck)
    val query = sqlu"""INSERT INTO DECKS VALUES ($deckId)"""
  }
  def readDeck(id: Int): List[String]

  def writeDeckList(decks: List[String]): List[Int]
  def readDeckList(id: Int): List[List[String]]

  def writePlayer(name: String, deck: String): Int = {
    val deckId : Int = writeDeck(Json.parse(deck).as[List[String]])
    val query = sqlu"""INSERT INTO PLAYERS VALUES ($name, $deckId);"""
    Await.result[Int](database.run(query), atMost = 10.second)
  }
  def readPlayer(id: Int): String

  def writePlayerList(players: List[String]): List[Int] = {
    val ids = ListBuffer[Int]()
    for (player <- players) {
      val playerJson: JsValue = Json.parse(player)
      val playerName: String = (playerJson \ "name").get.toString
      val playerDeck: String = (playerJson \ "playerDeck").get.as[String]
      ids += writePlayer(playerName, playerDeck)
    }
    ids.toList
  }
  def readPlayerList(id: Int): List[String]

  def writeTable(table: String): Unit = {
    val tableJson: JsValue = Json.parse(table)
    val playerIds: List[Int] = writePlayerList((tableJson \ "player").get.as[List[String]])
    val deckIds: List[Int] = writeDeckList((tableJson \ "tableDecks").get.as[List[String]])

    val query = sqlu"""INSERT INTO GAME_TABLES VALUES ('${playerIds.head}', '${playerIds.last}', '${deckIds.head}', '${deckIds.last}');"""
    Await.result(database.run(query), Duration.Inf)
  }
  def readTable(): String

}
