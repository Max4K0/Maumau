package de.htwg.se.maumau.model.gameBaseImpl

case class Player(name: String, playerDeck: Deck = Deck(List[Card]())) {
  override def toString: String = name
}
