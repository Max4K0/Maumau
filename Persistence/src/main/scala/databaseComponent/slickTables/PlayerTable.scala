package databaseComponent.slickTables

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import slick.jdbc.H2Profile.api._
import java.sql.Date
import scala.reflect.ClassTag

class PlayerTable(tag: Tag) extends Table[(String, Int)](tag, "PLAYERS"){
  def name = column[String]("NAME", O.PrimaryKey)
  def deck = column[Int]("DECK")
  override def * = (name, deck)
}
