package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.util.Observer



case class GUIApp(controller: ControllerInterface) extends Observer {
  val gui: GUI = new GUI( this, controller)



  val thread: Thread = new Thread {

    override def run(): Unit = {
      import javafx.embed.swing.JFXPanel
      new JFXPanel
      gui.main(Array())
    }
  }




  thread.start()
  controller.add( this )
  def exit( ):Unit = gui.stopApp()


  override def update: Boolean = {
    true
  }
}
