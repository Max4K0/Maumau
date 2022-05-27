package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class PlayerTable(tag: Tag) extends Table[(String, Int)](tag, "PLAYERS"){
  def name = column[String]("NAME", O.PrimaryKey)
  def deck = column[Int]("DECK")
  override def * = (name, deck)
}
