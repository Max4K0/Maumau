package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class CardTable(tag: Tag) extends Table[(Int, String)](tag, "CARDS"){
  def deckId = column[Int]("DECKID")
  def name = column[String]("NAME")
  override def * = (deckId, name)
}
