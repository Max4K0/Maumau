package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{Card, Deck, Player, Table}
import de.htwg.se.maumau.util.Observable

import scala.io.StdIn.readLine
import scala.util.Random

class Controller(var table: Table)extends Observable {

  val startDeck = Deck(List[Card]()).fillDeck.shuffleDeck(new Random(3))

  def testPlayeramount(amount: Int): Boolean = {
    amount match {
      case 2 | 3 | 4 => true
      case _ => false
    }
  }






  def refreshDeck(deck: Deck): Unit = {
   // table = table.refreshDeck(table, playerNumber)
//    notifyObservers()
  }

  override def toString: String = {
    table.toString
  }
}
