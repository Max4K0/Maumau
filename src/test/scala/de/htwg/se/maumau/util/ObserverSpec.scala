package de.htwg.se.maumau.util
import de.htwg.se.maumau.aview.TUI
import de.htwg.se.maumau.controller.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.maumau.model.{Player, Table}

import java.util.Observer

class ObserverSpec extends AnyWordSpec with Matchers{
  val table = Table()
  val controller = new Controller(table)
  val tui = TUI(controller)
  "Observer" when {
    "new" should {
      val obi = new Observable
      "obi should" in {
        controller.remove(tui)
      }

    }
  }
}