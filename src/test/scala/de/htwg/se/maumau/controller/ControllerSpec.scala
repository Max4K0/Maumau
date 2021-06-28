package de.htwg.se.maumau.controller

import de.htwg.se.maumau.Maumau.welcome
import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{Deck, Player, Table}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "controller" when {

    "new" should {
      val table = Table()
      val controller = new Controller()
      controller.visiblethememanager = 0
      controller.visiblecardthememanager = 0
      controller.checkCardLable = false
      controller.shouldUpdate = false
      val player = Player("", Deck())
      val welcome = new Welcome(controller)
      val tui =  TUI(controller)
      "addplayer" in {
        table.addPlayers(table, "", 0) should be(table.addPlayers(table, "", 0))
      }
      "Theme number at start should" in {
        controller.visiblethememanager should be(0)
        controller.changeThemeVis()
      }
      "Theme number after first change should" in {
        controller.visiblethememanager should be(1)
        controller.changeThemeVis()
      }

      "Theme number after second change should" in {
        controller.visiblethememanager should be(2)
        controller.changeThemeVis()
      }
      "Theme number after third change should" in {
        controller.visiblethememanager should be(0)
      }
      "Card theme number at start should" in {
        controller.visiblecardthememanager should be(0)
        controller.changeCardThemeVis()
      }
      "Card theme number after first change should" in {
        controller.visiblecardthememanager should be(1)
        controller.changeCardThemeVis()
      }
      "Card theme number after second change should" in {
        controller.visiblecardthememanager should be(0)
        controller.changeCardThemeVis()
      }
      "Card after valid check" in {
        controller.changeCheckCardLable(true)
        controller.checkCardLable should be(true)
      }
      "Update after changes should be" in {
        controller.changeShouldUpdate(true)
        controller.shouldUpdate should be(true)
      }
    }

  }
}