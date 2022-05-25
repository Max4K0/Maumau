package databaseComponent.slickTables

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import slick.jdbc.H2Profile.api._
import java.sql.Date
import scala.reflect.ClassTag

class CardTable(tag: Tag) extends Table[(Int, String)](tag, "CARDS"){
  def deckId = column[Int]("DECKID")
  def name = column[String]("NAME")
  override def * = (deckId, name)
}
