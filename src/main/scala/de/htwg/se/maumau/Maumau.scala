
package de.htwg.se.maumau


import de.htwg.se.maumau.aview.TUI
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model._

object Maumau {
  val controller = new Controller()
  val tui = new TUI(controller)
  //controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    tui.gamestart()
  }

}
