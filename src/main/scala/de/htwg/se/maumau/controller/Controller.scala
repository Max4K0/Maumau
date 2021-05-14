package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{Deck, Player, Table,TabelStrictStrategy}
import de.htwg.se.maumau.util.Observable

class Controller(var table: Table) extends Observable {

  def throwCard(cardNumber: Int): Unit = {
    table = table.throwCard(table, 0, cardNumber)
    notifyObservers()
  }
  def takeCard(): Unit = {
    table = table.takeCard(table, 0)
    notifyObservers()
  }

  def throwFirstCard(): Unit = {
    table = table.throwFirstCard(table)
    notifyObservers()
  }
  def checkCard(cardNumber: Int): Boolean = {
    (new TabelStrictStrategy).checkCard(table, 0, cardNumber)
    //table.checkCard(table, 0, cardNumber)
  }
  def checkDeck(): Boolean = {
   (new TabelStrictStrategy).checkDeck(table, 0)
   //table.checkDeck(table, 0)
  }

  def addPlayer(name: String, playerNum: Int): Unit = {
    //table = table.addPlayers(table, name, playerNum)
    table = (new TabelStrictStrategy).addPlayers(table, name, playerNum)
  }

  override def toString(): String = {
    table.toString()
  }

}
