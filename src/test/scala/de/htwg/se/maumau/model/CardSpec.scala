package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import de.htwg.se.maumau.model.Color._
//import de.htwg.se.maumau.model.Symbol._

class CardSpec extends AnyWordSpec with Matchers {

  "Card" when {
    "new" should {
      val card = Card(Color.Cross, Symbol.ASS)
      val card2 = Card(Color.Cross, Symbol.Two)
      val card3 = Card(Color.Cross, Symbol.Three)
      val card4 = Card(Color.Cross, Symbol.Four)
      val card5 = Card(Color.Cross, Symbol.Five)
      val card6 = Card(Color.Cross, Symbol.Six)
      val card7 = Card(Color.Cross, Symbol.Seven)
      val card8 = Card(Color.Cross, Symbol.Eight)
      val card9 = Card(Color.Cross, Symbol.Nine)
      val card10 = Card(Color.Cross, Symbol.Ten)
      val card11 = Card(Color.Diamonds, Symbol.Jack)
      val card12 = Card(Color.Spades, Symbol.Lady)
      val card13 = Card(Color.Heart, Symbol.King)
      "Symbol of card should" in {
        card.color should be(Color.Cross)
      }
      "Color of card should" in {
        card.symbol should be(Symbol.ASS)
      }
      "String of card should look like" in {
        card.toString should be("C A")
      }


      "Symbol of card2 should" in {
        card2.color should be(Color.Cross)
      }
      "Color of card2 should" in {
        card2.symbol should be(Symbol.Two)
      }
      "String of card2 should look like" in {
        card2.toString should be("C 2")
      }


      "Symbol of card3 should" in {
        card3.color should be(Color.Cross)
      }
      "Color of card3 should" in {
        card3.symbol should be(Symbol.Three)
      }
      "String of card3 should look like" in {
        card3.toString should be("C 3")
      }

      "String of card4 should look like" in {
        card4.toString should be("C 4")
      }
      "String of card5 should look like" in {
        card5.toString should be("C 5")
      }
      "String of card6 should look like" in {
        card6.toString should be("C 6")
      }
      "String of card7 should look like" in {
        card7.toString should be("C 7")
      }
      "String of card8 should look like" in {
        card8.toString should be("C 8")
      }
      "String of card9 should look like" in {
        card9.toString should be("C 9")
      }
      "String of card10 should look like" in {
        card10.toString should be("C 10")
      }
      "String of card11 should look like" in {
        card11.toString should be("D J")
      }
      "String of card12 should look like" in {
        card12.toString should be("S L")
      }
      "String of card13 should look like" in {
        card13.toString should be("H K")
      }
    }
  }
}

