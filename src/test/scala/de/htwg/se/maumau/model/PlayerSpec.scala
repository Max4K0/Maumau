package de.htwg.se.maumau.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers


class PlayerSpec extends AnyWordSpec with Matchers {

  "Player" when {
    "new" should {
      val player = Player("PlayerName")
      "name should" in {
        player.name should be("PlayerName")
      }
      "player deck" in {
        player.playerDeck should be(Deck())
      }
      "String representation" in {
        player.toString should be("PlayerName")
      }
    }
  }
}