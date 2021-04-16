package de.htwg.se.maumau.model

import de.htwg.se.maumau.model._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import  org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec with Matchers {

  "scala.Player" when {
    "new" should {
      val player = Player("scala.Player Name")
      "name should" in {
        player.name should be("scala.Player Name")
      }
      "String representation" in {
        player.toString should be("scala.Player Name")
      }
    }
  }
}