package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

case class GUI(controller: Controller) extends JFXApp {

  def Test(): Unit = {

  }

  object Test2 extends JFXApp {


    stage = new JFXApp.PrimaryStage {
      title.value = "Hello Stage"
      width = 600
      height = 450
      scene = new Scene {
        fill = LightGreen
        content = new Rectangle {
          x = 25
          y = 40
          width = 100
          height = 100
          fill <== when(hover) choose Green otherwise Red
        }
      }
    }
  }
}