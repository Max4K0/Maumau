package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class CardTable(tag: Tag) extends Table[(Int, Int, String)](tag, "cards"){
  def gameId = column[Int]("gameid")
  def deckId = column[Int]("deckid")
  def name = column[String]("name")
  def pk = primaryKey("card_pk", (gameId, deckId, name))
  override def * = (gameId, deckId, name)
}
