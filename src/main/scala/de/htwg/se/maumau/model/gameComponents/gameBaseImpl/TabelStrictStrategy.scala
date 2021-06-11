package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

class TabelStrictStrategy extends TabelStrategyTemplate{

  override def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean = {
    true
  }

  override def checkDeck(table: Table, playerNumber: Int): Boolean = {
    false
  }

//  def addPlayers(table: Table, name: String, playerNumber: Int): Table = {
//    val emptyDeck = Deck()
//    val changedDeck = table.tableDecks.head.throwCards(10, emptyDeck)
//    val newPlayer = Player(name, changedDeck._1)
//    val newDeck = changedDeck._2
//    val newTable = table.copy(player = table.player.updated(playerNumber, newPlayer), table.tableDecks.updated(0, newDeck))
//    newTable
//  }
}
