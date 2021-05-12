package de.htwg.se.maumau.aview

import de.htwg.se.maumau.Maumau
import de.htwg.se.maumau.Maumau.{controller, welcome}
import de.htwg.se.maumau.model.Table
import de.htwg.se.maumau.controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream

class TUISepc extends AnyWordSpec with Matchers {

  "TUI" when {
    "new" should {
      val table = Table()
      val controller = new Controller(table)
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()

      "tui invalid input should be" in {
        tui.processInputLine(input = "fdf") should be("invalid input")
      }
      "tui help input should be" in {
        tui.processInputLine(input = "help") should be("val id input")
      }
      /*"tui throw card input should be" in {
        tui.processInputLine(input = "throw card") should be("valid input")
      }
      "tui card input should be" in {
        tui.processInputLine(input = "3") should be("valid input")
      }*/
      "tui throw card input should be" in {
        val in = new ByteArrayInputStream("4".getBytes)
        Console.withIn(in) { tui.processInputLine("throw card") }
      }
      "tui valid game exit should be" in {
        tui.processInputLine(input = "q") should be("valid exit")
      }
    }
  }
}
