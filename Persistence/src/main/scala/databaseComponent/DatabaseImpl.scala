package databaseComponent

import com.google.inject.Inject
import databaseComponent.DatabaseInterface

import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*

import play.api.libs.json.{JsArray, JsValue, Json}

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


  def writeCard(card: String): Int
  def readCard(id: Int): String

  def writeCardList(cards: List[String]): List[Int]
  def readCardList(id: Int): List[String]

  def writeDeck(deck: List[String]): List[Int]
  def readDeck(id: Int): List[String]

  def writeDeckList(decks: List[String]): List[Int]
  def readDeckList(id: Int): List[List[String]]

  def writePlayer(player: String): Int
  def readPlayer(id: Int): String

  def writePlayerList(players: List[String]): List[Int] = {
    for (player <- players) {
      val playerJson: JsValue = Json.parse(player)
      val playerName: String = (playerJson \ "name").get.toString
      val playerDeck: List[String] = (playerJson \ "playerDeck").get.as[List[String]]
      
    }
  }
  def readPlayerList(id: Int): List[String]

  def writeTable(table: String): Unit = {
    val tableJson: JsValue = Json.parse(table)
    val playerIds: List[Int] = writePlayerList((tableJson \ "player").get.as[List[String]])
    val deckIds: List[Int] = writeDeckList((tableJson \ "tableDecks").get.as[List[String]])

    val query = sql"""INSERT INTO GAME_TABLES VALUES ('${playerIds.head}', '${playerIds.last}', '${deckIds.head}', '${deckIds.last}');""".as[(Int)]
    Await.result(database.run(query), Duration.Inf)
  }
  def readTable(): String

}
