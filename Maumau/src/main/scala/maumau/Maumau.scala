package maumau
import com.google.inject.Guice
import maumau.aview.{GUIApp, TUI, Welcome}
import maumau.controller.controllerComponent.ControllerInterface
import scala.io.StdIn.readLine
import fileIOComponent.IOAPI
import scala.util.{Failure, Success, Try}

case object Maumau {

  val injector = Guice.createInjector(new MaumauModul)
  val controller = injector.getInstance(classOf[ControllerInterface])
  try {
    controller.loadFile()
  }catch{
    case e : Exception => System.exit(1)
  }
  val welcome = new Welcome(controller)
  val tui =  TUI(controller)
  val gui = GUIApp(controller)

  def main(args: Array[String]): Unit = {


    /*
    Try(IOAPI) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)
    */
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
