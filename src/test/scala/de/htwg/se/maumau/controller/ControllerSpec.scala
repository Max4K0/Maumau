package de.htwg.se.maumau.controller

import de.htwg.se.maumau.Maumau.welcome
import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.gameBaseImpl.{Deck, Player, Table}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "controller" when {

    "new" should {
      val table = Table()
      val controller = new Controller(table)
      val player = Player("", Deck())
      val welcome = new Welcome(controller)
      val tui =  TUI(controller)
      "addplayer" in {
        table.addPlayers(table, "", 0) should be(table.addPlayers(table, "", 0))
      }
    }

  }
}