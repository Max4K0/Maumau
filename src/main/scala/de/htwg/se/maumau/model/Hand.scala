package de.htwg.se.maumau.model

import scala.collection.mutable.ListBuffer

case class Hand(playerHand: ListBuffer[Card] = ListBuffer[Card]()) {


  def seeHand(): ListBuffer[Card] = {
    playerHand
  }

  def addtoHand(card: Card): Hand = copy(playerHand = playerHand.addOne(card))

  //def cardDrop(cardindex: Int): Hand = copy(playerHand = playerHand.remove(cardindex))

  /*
    playerhand will bei cardDrop einen anderen rückgabetyp.
    Allerdings müsste er nichts zurückgeben nur removen.

    das Zweite ist: die klassen sind hier nur pro Spieler. Jeder Spieler soll
    eigene Werte bei den Aufrufen bekommen.
  */
}
