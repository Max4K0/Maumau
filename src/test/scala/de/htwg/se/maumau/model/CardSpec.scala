package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import de.htwg.se.maumau.model.Color._
//import de.htwg.se.maumau.model.Symbol._

class CardSpec extends AnyWordSpec with Matchers {

  "Card" when {
    "new" should {
      val card = Card(Color.Clubs, Symbol.ASS)
      val card2 = Card(Color.Diamonds, Symbol.King)
      "Symbol of card should" in {
        card.color should be(Color.Clubs)
      }
      "Color of card should" in {
        card.symbol should be(Symbol.ASS)
      }
//      "String of card should look like" in {
//        card.toString should be("C A")
//      }
//      "UTF 8 View of card should look like" in {
//        card.UTFSymbols should be("\u001B[31m\uD83C\uDCC1")
//      }

      "UTF 8 View of card should look like" in {
        card.toString should be("\u001B[31m\uD83C\uDCC1")
      }


      "Symbol of card2 should" in {
        card2.color should be(Color.Diamonds)
      }
      "Color of card2 should" in {
        card2.symbol should be(Symbol.King)
      }
//      "String of card2 should look like" in {
//        card2.toString should be("D K")
//      }
//      "UTF 8 View of card2 should look like" in {
//        card2.UTFSymbols should be("\u001B[30m\uD83C\uDCCE")
//      }

      "UTF 8 View of card2 should look like" in {
        card2.toString should be("\u001B[30m\uD83C\uDCCE")
      }


    }
  }
}

