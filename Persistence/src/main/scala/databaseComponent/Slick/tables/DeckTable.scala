package databaseComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.*

class DeckTable(tag: Tag) extends Table[(Int)](tag, "DECKS"){
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  override def * = (id)
}
