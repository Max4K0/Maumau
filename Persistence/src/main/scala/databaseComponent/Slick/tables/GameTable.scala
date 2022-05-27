package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class GameTable(tag: Tag) extends Table[(Int, String, String, Int, Int)](tag, "GAME_TABLE") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def playerOne = column[String]("PLAYERONE")
  def playerTwo = column[String]("PLAYERTWO")
  def deckOne = column[Int]("DECKONE")
  def deckTwo = column[Int]("DECKTWO")
  override def * = (id, playerOne, playerTwo, deckOne, deckTwo)
}
