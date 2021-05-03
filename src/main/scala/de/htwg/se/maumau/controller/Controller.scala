package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{Deck, Player, Table}
import de.htwg.se.maumau.util.Observable

class Controller(var table: Table)extends Observable {


  def refreshDeck(deck: Deck): Unit = {
   // table = table.refreshDeck(table, playerNumber)
//    notifyObservers()
  }

  override def toString: String = {
    table.toString
  }
}
