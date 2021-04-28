package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class DeckSpec extends AnyWordSpec with Matchers {

  val testDeck = new Deck

  val stdDeck = Deck()
  print(stdDeck)

  val shuffledDeck = stdDeck.shuffle(stdDeck)
  print(shuffledDeck)

  //val deck2 = Deck().getCard(shuffledDeck)

  val card = Deck().getCard(shuffledDeck).toString
  val shuffledDeck2 = shuffledDeck.drop(52)

  print(shuffledDeck2)
  print(shuffledDeck)
}