package de.htwg.se.maumau

import de.htwg.se.maumau.aview.TUI
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model._

import scala.io.StdIn.readLine
object Maumau {
  val table = Table()
  val controller = new Controller(table)
  val tui = new TUI(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {


    tui.welcome()
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
