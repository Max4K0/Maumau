package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class PlayerTable(tag: Tag) extends Table[(Int, String, Int)](tag, "players"){
  def gameId = column[Int]("gameid")
  def name = column[String]("name")
  def deck = column[Int]("deck")
  def pk = primaryKey("player_pk", (gameId, name))
  override def * = (gameId, name, deck)
}
