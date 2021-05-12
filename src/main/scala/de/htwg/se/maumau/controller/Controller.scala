package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{Deck, Player, Table}
import de.htwg.se.maumau.util.Observable

class Controller(var table: Table) extends Observable {

  def throwCard(cardNumber: Int): Unit = {
    table = table.throwCard(table, 0, cardNumber)
    notifyObservers()
  }

  def throwFirstCard(): Unit = {
    table = table.throwFirstCard(table)
    notifyObservers()
  }

  def addPlayer(name: String, playerNum: Int): Unit = {
    table = table.addPlayers(table, name, playerNum)
  }

  override def toString: String = {
    table.toString
  }
}
