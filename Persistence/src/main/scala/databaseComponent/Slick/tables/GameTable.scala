package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class GameTable(tag: Tag) extends Table[(Int, Option[String], Option[String], Option[Int], Option[Int])](tag, "game_table") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def playerOne = column[Option[String]]("playerone")
  def playerTwo = column[Option[String]]("playertwo")
  def deckOne = column[Option[Int]]("deckone")
  def deckTwo = column[Option[Int]]("decktwo")
  override def * = (id, playerOne, playerTwo, deckOne, deckTwo)
}
