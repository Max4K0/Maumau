package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{Card, Color, Deck, Symbol}

import scala.util.Random

class DeckSpec extends AnyWordSpec with Matchers {

  "Deck" when {
    "new" should {
      val testDeck = Deck(List[Card]())
      val fullDeck = Deck(List[Card]()).fillDeck
      val testPlayerDeck = Deck(List[Card]())
      val rendDeck = testDeck.fillDeck.shuffleDeck(new Random(2))
      "testDeck should contains cards" in {
        testDeck.cards should be(List[Card]())
      }
      "testDeck after fill" in {
        testDeck.fillDeck.cards should be(testDeck.fullDeck)
      }
      "testDeck after shuffle" in {
        fullDeck.shuffleDeck(new Random(2)) should not be(fullDeck)
        fullDeck.shuffleDeck(new Random(2)) should be(rendDeck)
      }
      "testDeck after throwDeck" in {
        testDeck.throwDeck.cards should be(List[Card]())
      }
      "testDeck after throwCards" in {
        testDeck.throwCards(5, testPlayerDeck)._1 should be(testDeck)
      }
      "fullDeck after throwOneCard (Throw the fifth Card)" in {
        fullDeck.throwOneCard(5, testPlayerDeck)._2 should be(fullDeck.throwOneCard(5, testPlayerDeck)._2)
      }
      "second Deck after fullDeck used throwOneCard (Throw the fifth Card)" in {
        fullDeck.throwOneCard(5, testPlayerDeck)._1 should be(Deck(List(Card(Some(Color.Clubs), Some(Symbol.Five)))))
      }
    }
  }
}