package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

abstract class AbstractTable(player: List[Player] , tableDecks: List[Deck]) {

  def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean
  def checkDeck(table: Table, playerNumber: Int): Boolean
  def addPlayers(table: Table, name: String, playerNumber: Int): Table
  def throwFirstCard(table: Table): Table
  def throwCard(table: Table, playerNumber: Int, cardNumber: Int): Table
  def takeCard(table: Table, playerNumber: Int): Table

}
