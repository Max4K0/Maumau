package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.Card
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.image._
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.{DarkGray, DarkRed, Red, White}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text

case class GUI (guiApp: GUIApp, controller: Controller) extends JFXApp {
      stage = new PrimaryStage {
        title = "Card Test"
        scene = new Scene(500,750) {
          val img = new Image(controller.table.player(1).playerDeck.cards.head.imgPath)
          val view = new ImageView(img)
          fill = Color.rgb(38, 38, 38)
          content = view
//          content = new HBox {
//            img
//            padding = Insets(50, 80, 50, 80)
//            children = Seq(
//              new Text {
//                text = "SCALA FX"
//                style = "-fx-font: normal bold 100pt sans-serif"
//                fill = new LinearGradient(
//                  endX = 0,
//                  stops = Stops(Red, DarkRed))
//                 //
//              },
//              new Text {
//                text = "\nTEST"
//                style = "-fx-font: italic bold 100pt sans-serif"
//                fill = new LinearGradient(
//                  endX = 0,
//                  stops = Stops(White, DarkGray)
//                )
//                effect = new DropShadow {
//                  color = DarkGray
//                  radius = 15
//                  spread = 0.25
//                }
//
//              }
//            )
//          }
        }
      }

}
