package maumau.model

import maumau.model
import maumau.model.gameComponents.gameBaseImpl._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import maumau.model.Color._
//import maumau.model.Symbol._

class CardSpec extends AnyWordSpec with Matchers {

  "Card" when {
    "new" should {
      val fullDeck = Deck().fillDeck
      val card = Card(Color.Clubs, Symbol.ASS)
      val card2 = Card(Color.Diamonds, Symbol.King)
      "Symbol of card should" in {
        card.color should be(Color.Clubs)
      }
      "Color of card should" in {
        card.symbol should be(Symbol.ASS)
      }
      "String of card should look like" in {
        card.toString should be("C A")
      }
      "Symbol of card2 should" in {
        card2.color should be(Color.Diamonds)
      }
      "Color of card2 should" in {
        card2.symbol should be(Symbol.King)
      }
      //------------------------------------------------------------------------------------------------------------------------------
      //---------------------------------------Testing String, UTF8, Image of a Card--------------------------------------------------
      //------------------------------------------------------------------------------------------------------------------------------

      "String of card2 should look like" in {
        card2.toString should be("D K")
      }
      "UTF8 of card2 should look like" in {
        card2.UTFSymbols should be("\u001B[31m\uD83C\uDCCE")
      }
      "Image path of card2 should look like" in {
        card2.imgPath should be("file:src/main/scala/de/htwg/se/maumau/util/textures/king_of_diamonds.png")
      }

      //------------------------------------------------------------------------------------------------------------------------------
      //--------------------------------------Testing String, UTF8, Image of all Cards------------------------------------------------
      //------------------------------------------------------------------------------------------------------------------------------
      "String of all cards should look like" in {
        fullDeck.cards.toString() should be(fullDeck.cards.toString())
      }
      "UTF8 of all cards should look like" in {
        fullDeck.cards.map(Card => Card.UTFSymbols) should be(fullDeck.cards.map(Card => Card.UTFSymbols))
      }
      "ImgPath of all cards should look like" in {
        fullDeck.cards.map(Card => Card.imgPath) should be(fullDeck.cards.map(Card => Card.imgPath))
      }
    }
  }
}

