package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class DeckTable(tag: Tag) extends Table[(Int, Int)](tag, "decks"){
  def gameId = column[Int]("gameid")
  def id = column[Int]("deckid", O.AutoInc)
  def pk = primaryKey("deck_pk", (gameId, id))
  override def * = (gameId, id)
}
