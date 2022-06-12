package de.htwg.se.maumau
import com.google.inject.Guice
import de.htwg.se.maumau.aview.{GUIApp, TUI, Welcome}
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine
import sys.process.*

object Maumau {

  val injector = Guice.createInjector(new MaumauModul)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val welcome = new Welcome(controller)

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    val tui = TUI(controller)
    println("Use GUI?")
    val input = readLine().toLowerCase()
    if (input == "y" || input == "yes"){
      val gui = GUIApp(controller)
    }
    controller.notifyObservers()
    var tuiInput = ""
    while (tuiInput != "quit") {
      tuiInput = readLine()
      tui.processInputLine(tuiInput)
    }
  }
}

//Music By: Benjamin Tissot (also known as Bensound)
//www.bensound.com
//https://www.bensound.com/royalty-free-music/track/jazzy-frenchy
