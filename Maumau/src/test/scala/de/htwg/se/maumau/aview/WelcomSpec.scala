package maumau.aview

import maumau.Maumau.welcome
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import maumau.model.gameComponents.gameBaseImpl.Table
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
      "successful welcome process should be" in {
        welcome.welcome() should be("game start successfully")
      }
    }
  }
}
