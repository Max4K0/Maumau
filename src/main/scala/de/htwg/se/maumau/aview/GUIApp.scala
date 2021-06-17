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
      //val music = new Media("FILE:///bensound-jazzyfrenchy.mp3")
      //val medplay =  new MediaPlayer(music)
      //medplay.volume = 0.1
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
