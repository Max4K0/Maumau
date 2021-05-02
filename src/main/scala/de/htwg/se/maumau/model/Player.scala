package de.htwg.se.maumau.model

case class Player(name: String, playerDeck: Deck = Deck(List[Card]())) {
  override def toString: String = name
}
