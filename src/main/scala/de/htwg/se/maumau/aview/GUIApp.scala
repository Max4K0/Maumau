package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.Observer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text



case class GUIApp( val controller:Controller ) extends Observer {
  val gui: GUI = new GUI( this, controller)
  val thread: Thread = new Thread {
    override def run(): Unit = {
      gui.main(Array())
    }
  }
  thread.start()
  controller.add( this )
  def exit( ):Unit = gui.stopApp()

  override def update: Boolean = true
}
