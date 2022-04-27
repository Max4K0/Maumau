package maumau.util

import maumau.Maumau
import maumau.aview.{TUI, Welcome}
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import maumau.model.gameComponents.gameBaseImpl.Table

import java.security.KeyStore.TrustedCertificateEntry
import maumau.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream
import scala.collection.mutable.Stack

class UndoManagerSpec extends AnyWordSpec with Matchers {
  "An UndoManager" when {
    "new" should {
      val table = Table()
      val controller = new Controller()
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()

      "witch empty stack" in {
        controller.tables = Stack[Table]()
        controller.states = Stack[String]("")
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