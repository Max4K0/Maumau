package de.htwg.se.maumau

import de.htwg.se.maumau.aview.{TUI, GUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model._

object Maumau {
  val table = Table()
  val controller = new Controller(table)
  val welcome = new Welcome(controller)
  val tui =  TUI(controller)
  val gui = GUI(controller)
  //controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    //controller.notifyObservers()
    gui.Test()

    "GameOver"
  }
}
