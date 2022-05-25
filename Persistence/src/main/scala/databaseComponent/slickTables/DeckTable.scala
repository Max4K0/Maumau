package databaseComponent.slickTables

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import slick.jdbc.H2Profile.api._
import java.sql.Date
import scala.reflect.ClassTag

class DeckTable(tag: Tag) extends Table[(Int)](tag, "DECKS"){
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  override def * = (id)
}
