package de.htwg.se.maumau

import de.htwg.se.maumau.aview.{GUI, GUIApp, TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model._

object Maumau {
  val table = Table()
  val controller = new Controller(table)
  val welcome = new Welcome(controller)
  val tui =  TUI(controller)
  val gui = new GUIApp(controller)
  //controller.notifyObservers()


  //
  //Music By: Benjamin Tissot (also known as Bensound)
  //www.bensound.com
  //

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    //controller.notifyObservers()

    "GameOver"
  }
}
