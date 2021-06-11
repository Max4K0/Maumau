package de.htwg.se.maumau

import de.htwg.se.maumau.aview.{GUIApp, TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model._
import de.htwg.se.maumau.model.gameBaseImpl.Table

import scala.io.StdIn.readLine

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
  //https://www.bensound.com/royalty-free-music/track/jazzy-frenchy

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    var input: String = ""

    while (input != "q") {
      input = readLine()
      tui.processInputLine(input)
    }
    System.exit(0)
  }
}
