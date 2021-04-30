package de.htwg.se.maumau.model

import de.htwg.se.maumau.model

import scala.collection.mutable.ListBuffer
import scala.util.Random
//Wird diese Klasse in controller verschoben, so kommen Fehler.
case class Deck(cards: ListBuffer[Card] = ListBuffer[Card]()) {
  val fullDeck = ListBuffer[Card](
    Card(Color.Clubs, Symbol.ASS),
    model.Card(Color.Clubs, Symbol.Two),
    model.Card(Color.Clubs, Symbol.Three),
    model.Card(Color.Clubs, Symbol.Four),
    model.Card(Color.Clubs, Symbol.Five),
    model.Card(Color.Clubs, Symbol.Six),
    model.Card(Color.Clubs, Symbol.Seven),
    model.Card(Color.Clubs, Symbol.Eight),
    model.Card(Color.Clubs, Symbol.Nine),
    model.Card(Color.Clubs, Symbol.Ten),
    model.Card(Color.Clubs, Symbol.Jack),
    model.Card(Color.Clubs, Symbol.Lady),
    model.Card(Color.Clubs, Symbol.King),
    model.Card(Color.Spades, Symbol.ASS),
    model.Card(Color.Spades, Symbol.Two),
    model.Card(Color.Spades, Symbol.Three),
    model.Card(Color.Spades, Symbol.Four),
    model.Card(Color.Spades, Symbol.Five),
    model.Card(Color.Spades, Symbol.Six),
    model.Card(Color.Spades, Symbol.Seven),
    model.Card(Color.Spades, Symbol.Eight),
    model.Card(Color.Spades, Symbol.Nine),
    model.Card(Color.Spades, Symbol.Ten),
    model.Card(Color.Spades, Symbol.Jack),
    model.Card(Color.Spades, Symbol.Lady),
    model.Card(Color.Spades, Symbol.King),
    model.Card(Color.Diamonds, Symbol.ASS),
    model.Card(Color.Diamonds, Symbol.Two),
    model.Card(Color.Diamonds, Symbol.Three),
    model.Card(Color.Diamonds, Symbol.Four),
    model.Card(Color.Diamonds, Symbol.Five),
    model.Card(Color.Diamonds, Symbol.Six),
    model.Card(Color.Diamonds, Symbol.Seven),
    model.Card(Color.Diamonds, Symbol.Eight),
    model.Card(Color.Diamonds, Symbol.Nine),
    model.Card(Color.Diamonds, Symbol.Ten),
    model.Card(Color.Diamonds, Symbol.Jack),
    model.Card(Color.Diamonds, Symbol.Lady),
    model.Card(Color.Diamonds, Symbol.King),
    model.Card(Color.Hearts, Symbol.ASS),
    model.Card(Color.Hearts, Symbol.Two),
    model.Card(Color.Hearts, Symbol.Three),
    model.Card(Color.Hearts, Symbol.Four),
    model.Card(Color.Hearts, Symbol.Five),
    model.Card(Color.Hearts, Symbol.Six),
    model.Card(Color.Hearts, Symbol.Seven),
    model.Card(Color.Hearts, Symbol.Eight),
    model.Card(Color.Hearts, Symbol.Nine),
    model.Card(Color.Hearts, Symbol.Ten),
    model.Card(Color.Hearts, Symbol.Jack),
    model.Card(Color.Hearts, Symbol.Lady),
    model.Card(Color.Hearts, Symbol.King))
  val emptyDeck = ListBuffer[Card]()

  def throwDeck: Deck = copy(cards = cards.drop(cards.size))

  def fillDeck: Deck = copy(cards = cards.addAll(fullDeck))

  def shuffleDeck: Deck = copy(cards = Random.shuffle(cards))

  def throwCard(deck1: Deck): (Deck, Deck) = (copy(deck1.cards.addOne(cards.head)), copy(cards.drop(1)))

  //  def throwCards(deck: ListBuffer[Card], deck2: ListBuffer[Card], number : Int) = {
  //      for (i <- 1 to number if deck.size > 0) deck2. (getCard(deck)._2)
  //     }
  //
  //    def getDeck(deck : ListBuffer[Card], card: Card): Deck = copy(deck.drop(deck.size)
}
