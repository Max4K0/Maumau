package de.htwg.se.maumau.aview

import de.htwg.se.maumau.Maumau.welcome
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WelcomSpec extends AnyWordSpec with Matchers {

  "welcome" when {
    "new" should {
      val table = Table()
      val controller = new Controller()
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()
      "tui invalid input should be" in {
        welcome.welcome() should be("game start successfully")
      }
    }
  }
}
