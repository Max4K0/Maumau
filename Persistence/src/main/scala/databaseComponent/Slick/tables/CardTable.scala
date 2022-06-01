package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

case class Card(deckId: Int, name: String)

class CardTable(tag: Tag) extends Table[Card](tag, "CARDS"){
  def deckId = column[Int]("DECKID")
  def name = column[String]("NAME")
  override def * = (deckId, name)
}
