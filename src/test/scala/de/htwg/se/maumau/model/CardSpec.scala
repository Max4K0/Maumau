package de.htwg.se.maumau.model
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers


class PlayerSpec extends AnyWordSpec with Matchers {

  "Card" when {
    "new" should {
      val Card = Card("scala.Player Name")
      "name should" in {
        player.name should be("scala.Player Name")
      }
      "String representation" in {
        player.toString should be("scala.Player Name")
      }
    }
  }
}