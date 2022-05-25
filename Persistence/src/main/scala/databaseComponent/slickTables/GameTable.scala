package databaseComponent.slickTables

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import slick.jdbc.H2Profile.api._
import java.sql.Date
import scala.reflect.ClassTag

class GameTable(tag: Tag) extends Table[(String, String, Int, Int)](tag, "GAME_TABLE") {
  def playerOne = column[String]("PLAYERONE")
  def playerTwo = column[String]("PLAYERTWO")
  def deckOne = column[Int]("DECKONE")
  def deckTwo = column[Int]("DECKTWO")
  override def * = (playerOne, playerTwo, deckOne, deckTwo)
}
