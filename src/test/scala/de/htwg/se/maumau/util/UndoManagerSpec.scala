package de.htwg.se.maumau.util

import de.htwg.se.maumau.Maumau
import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table

import java.security.KeyStore.TrustedCertificateEntry
import de.htwg.se.maumau.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream
import scala.collection.mutable
import scala.collection.mutable.Stack

class UndoManagerSpec extends AnyWordSpec with Matchers {
  "An UndoManager" when {
    "new" should {
      val table = Table()
      class TestController(override val tables: mutable.Stack[Table], override val states: mutable.Stack[String]) extends Controller(){}
      val controller = new TestController(mutable.Stack[Table](), mutable.Stack[String](""))
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()

      "witch empty stack" in {
        State.state=""
        tui.processInputLine("redo") should be("invalid redo")
        controller.undo should be
        controller.redo should be
        State.state=""
        tui.processInputLine("undo") should be("invalid undo")
      }


    }
  }
}