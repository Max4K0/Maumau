package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

trait TableStrategyTemplate {
  def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean

  def checkDeck(table: Table, playerNumber: Int): Boolean
  //def addPlayers(table: Table, name: String, playerNumber: Int): Table
}
