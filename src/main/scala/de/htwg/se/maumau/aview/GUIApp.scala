package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.Observer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.media._
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.media.AudioClip
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text

import scala.reflect.io.File



case class GUIApp( val controller:Controller ) extends Observer {
  val gui: GUI = new GUI( this, controller)



  val thread: Thread = new Thread {

    override def run(): Unit = {
      import javafx.embed.swing.JFXPanel
      new JFXPanel
      val music = new Media("FILE:///bensound-jazzyfrenchy.mp3")
      val medplay =  new MediaPlayer(music)
      medplay.volume = 0.1
      //medplay.autoPlay = (true)
      //medplay.onRepeat()
     // medplay.setAutoPlay(true)


      //val music = new Media("https://www.bensound.com/royalty-free-music/track/jazzy-frenchy/bensound-jazzyfrenchy.mp3")
      //val music = new Media(getClass().getClassLoader().getResource("//bensound-jazzyfrenchy.mp3").toString())

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
