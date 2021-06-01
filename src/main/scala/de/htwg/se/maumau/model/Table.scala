package de.htwg.se.maumau.model
import de.htwg.se.maumau.util.{State, nextPlayerEvent, winEvent}

import scala.util.Random
case class Table(player: List[Player] = List[Player](Player("P1", Deck()), Player("P2", Deck())), tableDecks: List[Deck] = List[Deck](Deck().fillDeck.shuffleDeck(new Random(1)),Deck(List[Card]()))) extends AbstractTable(player: List[Player] , tableDecks: List[Deck]){


  override def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean = {
    val currentPlayer = table.player(playerNumber)
    ((currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == tableDecks(1).cards.last.symbol) ||
      (currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.color == tableDecks(1).cards.last.color) ||
      currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == Symbol.Jack)
  }

  override def checkDeck(table: Table, playerNumber: Int): Boolean = {
    val currentPlayer = table.player(playerNumber)
    currentPlayer.playerDeck.cards.map(Card => Card.symbol).contains(tableDecks(1).cards.last.symbol) ||
      currentPlayer.playerDeck.cards.map(Card => Card.color).contains(tableDecks(1).cards.last.color)
  }

  override def addPlayers(table: Table, name: String, playerNumber: Int): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwCards(5, emptyDeck)
    val newPlayer = Player(name, changedDeck._1)
    val newDeck = changedDeck._2
    val newTable = table.copy(player = table.player.updated(playerNumber, newPlayer), tableDecks.updated(0, newDeck))
    newTable
  }

  override def throwFirstCard(table: Table): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwOneCard(1, emptyDeck)
    val newTable = table.copy(player = player,tableDecks.updated(1, changedDeck._1).updated(0, changedDeck._2))
    newTable
  }

  override def throwCard(table: Table, playerNumber: Int, cardNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = currentPlayer.playerDeck.throwOneCard(cardNumber, tableDecks(1))
    val newPlayer = Player(currentPlayer.name,changedDeck._2)
    val newTable = table.copy(player.updated(playerNumber, newPlayer), tableDecks.updated(1,changedDeck._1))
    val result = if (newTable.player(playerNumber).playerDeck.equals(Deck())) {
      State.handle(winEvent())
      println(State.state)
      //System.exit(1)
      table
    } else State.handle(nextPlayerEvent())
    newTable
  }

  override def takeCard(table: Table, playerNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = tableDecks.head.throwCards(1, currentPlayer.playerDeck)
    val newPlayer = Player(currentPlayer.name,changedDeck._1)
    val newTable = table.copy(player.updated(playerNumber, newPlayer), tableDecks.updated(0,changedDeck._2))
    newTable
  }


  override def toString(): String = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    val table = new StringBuilder("\u001B[48;5;15m" + " tablecards: ")
    table.append(tableDecks(1).cards.last.UTFSymbols)
    val hand = new StringBuilder(" Player" + (playerNumber+1) +": ")

    hand.append(player(playerNumber).playerDeck.cards.map(Card => Card.UTFSymbols).mkString(" "))

    //hand.append(player(playerNumber).playerDeck.cards.map(Card => Card.Path).mkString(" "))
    val Statement = new StringBuilder()
    Statement.append(table + "\n\n")
    Statement.append(hand)
    Statement.toString()
  }

}

