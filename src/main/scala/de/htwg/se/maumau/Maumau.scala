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
    var input: String = ""
    println("Use GUI?")
    input = readLine()
    if (input == "y"){
      val gui = GUIApp(controller)
    }

    input = ""
    while (input != "quit") {
      input = readLine()
      tui.processInputLine(input)
    }
    controller.saveFile()
  }
}

//Music By: Benjamin Tissot (also known as Bensound)
//www.bensound.com
//https://www.bensound.com/royalty-free-music/track/jazzy-frenchy
