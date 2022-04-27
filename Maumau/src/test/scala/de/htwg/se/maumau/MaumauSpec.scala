package maumau

import maumau.aview.{TUI, Welcome}
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import maumau.model.gameComponents.gameBaseImpl.Table

import java.io.ByteArrayInputStream
import scala.util.Random

class MaumauSpec extends AnyWordSpec with Matchers {

  "Maumau" when {
    val table = Table()
    val controller = new Controller()
    val welcome = new Welcome(controller)
    val tui =  TUI(controller)

    "throw card " in {
      val in = new ByteArrayInputStream("quit".getBytes())
      Console.withIn(in) {
        Maumau.main(Array("3", "q"))
      }should be
    }
    "run normal and quit" in {
      val in = new ByteArrayInputStream("quit".getBytes)
      Console.withIn(in) { Maumau.main(Array()) } should be
    }
  }
}