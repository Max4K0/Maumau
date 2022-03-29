package de.htwg.se.maumau
import com.google.inject.{Guice, Injector}
import de.htwg.se.maumau.aview.{GUIApp, TUI, Welcome}
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Maumau {

  val injector: Injector = Guice.createInjector(new MaumauModul)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val welcome = new Welcome(controller)
  val tui: TUI =  TUI(controller)
  val gui = new GUIApp(controller)
  controller.loadFile()

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    var input: String = ""
    while (input != "quit") {
      input = readLine()
      tui.processInputLine(input)
    }
  }
}

//Music By: Benjamin Tissot (also known as Bensound)
//www.bensound.com
//https://www.bensound.com/royalty-free-music/track/jazzy-frenchy
