package de.htwg.se.maumau.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.model.Symbol._
import de.htwg.se.maumau.model.Color._

class CardSpec extends AnyWordSpec with Matchers {

  "Card" when {
    "new" should {
      val card = Card(Cross, ASS)
      "Symbol should" in {
        card.color should be(Cross)
      }
      "Color should" in {
        card.symbol should be(ASS)
      }
      "String should look like" in {
        card.toString should be("Cross ASS")
      }
    }
  }
}