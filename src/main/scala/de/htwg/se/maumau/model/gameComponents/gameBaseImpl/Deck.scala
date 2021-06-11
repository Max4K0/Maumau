package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

import scala.util.Random

case class Deck(cards: List[Card] = List[Card]()) {
  val fullDeck = List[Card](
    Card(Some(Color.Clubs), Some(Symbol.ASS)),
    Card(Some(Color.Clubs), Some(Symbol.Two)),
    Card(Some(Color.Clubs), Some(Symbol.Three)),
    Card(Some(Color.Clubs), Some(Symbol.Four)),
    Card(Some(Color.Clubs), Some(Symbol.Five)),
    Card(Some(Color.Clubs), Some(Symbol.Six)),
    Card(Some(Color.Clubs), Some(Symbol.Seven)),
    Card(Some(Color.Clubs), Some(Symbol.Eight)),
    Card(Some(Color.Clubs), Some(Symbol.Nine)),
    Card(Some(Color.Clubs), Some(Symbol.Ten)),
    Card(Some(Color.Clubs), Some(Symbol.Jack)),
    Card(Some(Color.Clubs), Some(Symbol.Lady)),
    Card(Some(Color.Clubs), Some(Symbol.King)),
    Card(Some(Color.Spades), Some(Symbol.ASS)),
    Card(Some(Color.Spades), Some(Symbol.Two)),
    Card(Some(Color.Spades), Some(Symbol.Three)),
    Card(Some(Color.Spades), Some(Symbol.Four)),
    Card(Some(Color.Spades), Some(Symbol.Five)),
    Card(Some(Color.Spades), Some(Symbol.Six)),
    Card(Some(Color.Spades), Some(Symbol.Seven)),
    Card(Some(Color.Spades), Some(Symbol.Eight)),
    Card(Some(Color.Spades), Some(Symbol.Nine)),
    Card(Some(Color.Spades), Some(Symbol.Ten)),
    Card(Some(Color.Spades), Some(Symbol.Jack)),
    Card(Some(Color.Spades), Some(Symbol.Lady)),
    Card(Some(Color.Spades), Some(Symbol.King)),
    Card(Some(Color.Diamonds), Some(Symbol.ASS)),
    Card(Some(Color.Diamonds), Some(Symbol.Two)),
    Card(Some(Color.Diamonds), Some(Symbol.Three)),
    Card(Some(Color.Diamonds), Some(Symbol.Four)),
    Card(Some(Color.Diamonds), Some(Symbol.Five)),
    Card(Some(Color.Diamonds), Some(Symbol.Six)),
    Card(Some(Color.Diamonds), Some(Symbol.Seven)),
    Card(Some(Color.Diamonds), Some(Symbol.Eight)),
    Card(Some(Color.Diamonds), Some(Symbol.Nine)),
    Card(Some(Color.Diamonds), Some(Symbol.Ten)),
    Card(Some(Color.Diamonds), Some(Symbol.Jack)),
    Card(Some(Color.Diamonds), Some(Symbol.Lady)),
    Card(Some(Color.Diamonds), Some(Symbol.King)),
    Card(Some(Color.Hearts), Some(Symbol.ASS)),
    Card(Some(Color.Hearts), Some(Symbol.Two)),
    Card(Some(Color.Hearts), Some(Symbol.Three)),
    Card(Some(Color.Hearts), Some(Symbol.Four)),
    Card(Some(Color.Hearts), Some(Symbol.Five)),
    Card(Some(Color.Hearts), Some(Symbol.Six)),
    Card(Some(Color.Hearts), Some(Symbol.Seven)),
    Card(Some(Color.Hearts), Some(Symbol.Eight)),
    Card(Some(Color.Hearts), Some(Symbol.Nine)),
    Card(Some(Color.Hearts), Some(Symbol.Ten)),
    Card(Some(Color.Hearts), Some(Symbol.Jack)),
    Card(Some(Color.Hearts), Some(Symbol.Lady)),
    Card(Some(Color.Hearts), Some(Symbol.King)))

  val emptyDeck = List[Card]()

  def throwDeck: Deck = copy(cards = cards.drop(cards.size))

  def fillDeck: Deck = copy(cards = cards.appendedAll(fullDeck))

  //  def takeOneCard(cardNumber: Integer = 1, deck1: Deck): (Deck, Deck) = (copy(deck1.cards.appendedAll(cards.slice(cardNumber-1, cardNumber))), copy(cards.patch(cardNumber-1, Nil, 1)))
  def shuffleDeck(random: Random): Deck = copy(cards = random.shuffle(cards))

  def throwCards(dropNumber: Integer, deck1: Deck): (Deck, Deck) = (copy(deck1.cards.appendedAll(cards.takeRight(dropNumber))), copy(cards.dropRight(dropNumber)))

  def throwOneCard(cardNumber: Integer, deck1: Deck): (Deck, Deck) = (copy(deck1.cards.appendedAll(cards.slice(cardNumber - 1, cardNumber))), copy(cards.patch(cardNumber - 1, Nil, 1)))
}
