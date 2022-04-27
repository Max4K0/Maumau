package maumau.model.gameComponents.gameBaseImpl

case class Player(name: String, playerDeck: Deck = Deck(List[Card]())) {
  override def toString: String = name
}
