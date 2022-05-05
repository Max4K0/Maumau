package maumau
import com.google.inject.{Guice, Injector}
import maumau.aview.{GUIApp, TUI, Welcome}
import maumau.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine
import fileIOComponent.IOAPI

import scala.util.{Failure, Success, Try}

object Maumau {
  def main(args: Array[String]): Unit = {
    val injector: Injector = Guice.createInjector(new MaumauModul)
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    val welcome = new Welcome(controller)
    welcome.welcome()
    val gui: GUIApp = GUIApp(controller)
    val tui: TUI = TUI(controller)
    controller.changeShouldUpdate(true)
    var input: String = ""
    while (input != "quit") {
      input = readLine()
      tui.processInputLine(input)
    }
    System.exit(0)
  }
}

//Music By: Benjamin Tissot (also known as Bensound)
//www.bensound.com
//https://www.bensound.com/royalty-free-music/track/jazzy-frenchy
