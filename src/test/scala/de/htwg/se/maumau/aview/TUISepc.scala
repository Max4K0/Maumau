package de.htwg.se.maumau.aview

import de.htwg.se.maumau.Maumau
import de.htwg.se.maumau.Maumau.{controller, welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.gameBaseImpl.Table
import de.htwg.se.maumau.util.{State, winEvent}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream
import scala.util.Random

class TUISepc extends AnyWordSpec with Matchers {

  "TUI" when {
    "new" should {
      val table = Table()
      val controller = new Controller(table)
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()


      "tui invalid input should be" in {
        State.state = ""
              tui.processInputLine("r") should be("invalid redo")
              tui.processInputLine("z") should be("invalid undo")
              tui.processInputLine(input = "fdf") should be("invalid input")
      }
      "tui help input should be" in {
        tui.processInputLine(input = "help") should be("valid input")
      }
     "tui valid throw card input should be" in {
       val in = new ByteArrayInputStream("3".getBytes)
       Console.withIn(in) { tui.processInputLine("throw card") should be("valid throw")
         State.state should be("Player1:")
         tui.processInputLine("r") should be("valid redo")
         State.state should be("Player1:")
         tui.processInputLine("z") should be("valid undo")
       }
     }
      "tui invalid throw card input with strategy 1 should be" in {
        val in = new ByteArrayInputStream("1".getBytes)
        Console.withIn(in) { tui.processInputLine("throw card") should be("invalid throw")}
      }
      "tui valid throw card input with strategy 2 should be" in {
        val in = new ByteArrayInputStream("2".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("valid strategy")}
        val in2 = new ByteArrayInputStream("1".getBytes)
        Console.withIn(in2) { tui.processInputLine("throw card") should be("valid throw")}
      }




     "tui valid game exit should be" in {
       tui.processInputLine(input = "q") should be("invalid input")
     }
      "tui valid take card should be" in {
        val in = new ByteArrayInputStream("2".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("valid strategy")}
        tui.processInputLine(input = "take card") should be("invalid pull")
        tui.processInputLine("r") should be("valid redo")
        tui.processInputLine("z") should be("valid undo")
      }
      "tui invalid take card should be" in {
        val in = new ByteArrayInputStream("1".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("valid strategy")}
        tui.processInputLine(input = "take card") should be("invalid pull")
        tui.processInputLine("r") should be("valid redo")
        tui.processInputLine("z") should be("valid undo")
      }
      "tui invalid strategy should be" in {
        val in = new ByteArrayInputStream("11".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("invalid strategy")}
      }
      "tui win should be" in {
        val in = new ByteArrayInputStream("2".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("valid strategy")}
        val in2 = new ByteArrayInputStream("1".getBytes)
        var a = 0
        for (a <- 0 to 8)
          controller.throwCard(1)
        State.handle(winEvent())
      }
      "tui take take should be" in {
        val in = new ByteArrayInputStream("2".getBytes)
        Console.withIn(in) { tui.processInputLine("change strat") should be("valid strategy")}
        tui.processInputLine(input = "take card")
        tui.processInputLine(input = "take card")
        tui.processInputLine(input = "take card")
        controller.checkDeck()
      }
      "tui throw Jack card input should be" in {
        val in = new ByteArrayInputStream("4".getBytes)
        Console.withIn(in) {tui.processInputLine("throw card")should be("valid throw")}
      }

     // "tui valid game take card should be" in {
     //   tui.processInputLine(input = "take card") should not be("invalid pull")
     // }


    }
  }
 //"TUI 2" when {
 //  "new 2" should {
 //    var table2 = Table()
 //    val controller2 = new Controller(table2)
 //    val welcome2 = new Welcome(controller2)
 //    val tui2 = TUI(controller2)
 //    welcome2.welcome()

 //    "tui2 invalid take card should be" in {
 //      table2 = Table()
 //      tui2.processInputLine("r") should be("invalid redo")
 //      tui2.processInputLine("z") should be("invalid undo")
 //    }
 //  }
  //}
}
