package de.htwg.se.maumau.model

import de.htwg.se.maumau.controller.Player
import de.htwg.se.maumau.model._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec with Matchers {

  "Player" when {
    "new" should {
      val player = Player("PlayerName")
      "name should" in {
        player.name should be("PlayerName")
      }
      "String representation" in {
        player.toString should be("PlayerName")
      }
    }
  }
}