package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{Deck, Player, TabelStrictStrategy, Table}
import de.htwg.se.maumau.util.{Observable, State}

class Controller(var table: Table) extends Observable {

  def throwCard(cardNumber: Int): Unit = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    table = table.throwCard(table, playerNumber, cardNumber)
    notifyObservers()
  }
  def takeCard(): Unit = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    table = table.takeCard(table, playerNumber)
    notifyObservers()
  }

  def throwFirstCard(): Unit = {
    table = table.throwFirstCard(table)
    notifyObservers()
  }
  def checkCard(cardNumber: Int): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    //(new TabelStrictStrategy).checkCard(table, playerNumber, cardNumber)
    table.checkCard(table, playerNumber, cardNumber)
  }
  def checkDeck(): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
   (new TabelStrictStrategy).checkDeck(table, playerNumber)
   //table.checkDeck(table, playerNumber)
  }

  def addPlayer(name: String, playerNum: Int): Unit = {
    table = table.addPlayers(table, name, playerNum)
   // table = (new TabelStrictStrategy).addPlayers(table, name, playerNum)
  }

  override def toString(): String = {
    table.toString()
  }

}
