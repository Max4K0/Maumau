package de.htwg.se.maumau
import com.google.inject.Guice
import de.htwg.se.maumau.aview.{GUIApp, TUI, Welcome}
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.model._
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table

import scala.io.StdIn.readLine

object Maumau {

  val injector = Guice.createInjector(new MaumauModul)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val welcome = new Welcome(controller)
  val tui =  TUI(controller)
  val gui = new GUIApp(controller)

  def main(args: Array[String]): Unit = {
    welcome.welcome()
    var input: String = ""
    while (input != "quit") {
      input = readLine()
      tui.processInputLine(input)
    }
  }
}



//controller.notifyObservers()
//
//Music By: Benjamin Tissot (also known as Bensound)
//www.bensound.com
//https://www.bensound.com/royalty-free-music/track/jazzy-frenchy
