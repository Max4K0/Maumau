package de.htwg.se.maumau.model

trait TabelStrategyTemplate {
  def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean
  def checkDeck(table: Table, playerNumber: Int): Boolean
  def addPlayers(table: Table, name: String, playerNumber: Int): Table
}
