package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.model.{Card, Color, Deck, Symbol}
import scala.collection.mutable.ListBuffer

class DeckSpec extends AnyWordSpec with Matchers {

  "Deck" when {
    "new" should {
      val testDeck = Deck()
      val testPlayerDeck = Deck()
      "testDeck should contains cards" in {
        testDeck.cards should be(ListBuffer[Card]())
      }
      "testDeck after fill" in {
        testDeck.fillDeck.cards should be(testDeck.fullDeck)
      }
      "testDeck after shuffle" in {
        testDeck.shuffleDeck should not be(testDeck.cards)
      }
      "testDeck after throwDeck" in {
        testDeck.throwDeck.cards should be(ListBuffer[Card]())
      }
//      "testDeck after throwCard" in {
//        testDeck.throwCard(testPlayerDeck) should be(testDeck.cards)
//      }
    }
  }
}