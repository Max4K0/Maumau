package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.Card
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{BoundingBox, Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.{Scene, SubScene}
import scalafx.scene.effect.{DropShadow, Light, Lighting}
import scalafx.scene.image._
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{FlowPane, GridPane, HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text

case class GUI (guiApp: GUIApp, controller: Controller) extends JFXApp {



   stage = new PrimaryStage {

     title = "Card Test"

     scene = new Scene {
       val cardRatio = 0.5
       val cardSizeX = 500 * cardRatio
       val cardSizeY = 726 * cardRatio
       val img = new Image(controller.table.player(1).playerDeck.cards.head.imgPath, cardSizeX, cardSizeY, true, false)
       val view = new ImageView(img)
       content += new HBox {

         padding = Insets(100, 100, 700, 700)
         children = Seq(

           new Button() {
           this.setGraphic(view)
           this.setStyle("-fx-background-color: transparent")
           effect = new Lighting
           this.visible = true
           this.setLayoutX(0)
         },

         new Button() {
           this.setGraphic(new ImageView(new Image(controller.table.player(1).playerDeck.cards.last.imgPath, cardSizeX, cardSizeY, true, false)))
           this.setStyle("-fx-background-color: transparent")
           effect = new Lighting
           this.visible = true
           this.setLayoutX(0)
         }
         )
       }
       content += new HBox {
         padding = Insets(500, 0, 0, 0)
         children = Seq(

           new Button() {
             this.setGraphic(new ImageView(new Image(controller.table.player(0).playerDeck.cards.head.imgPath, cardSizeX, cardSizeY, true, false)))
             this.setStyle("-fx-background-color: transparent")
             effect = new Lighting
             this.visible = true
             //this.setLayoutX(0)
           },

           new Button() {
             this.setGraphic(new ImageView(new Image(controller.table.player(0).playerDeck.cards.last.imgPath, cardSizeX, cardSizeY, true, false)))
             this.setStyle("-fx-background-color: transparent")
             effect = new Lighting
             this.visible = true
             //this.setLayoutX(0)
           }

         )
       }
       for (x <- 0 to 1)
         content += new HBox {
           padding = Insets(500 * x , 0, 0, 0)
             children = Seq(
               new Button() {
                 this.setGraphic(new ImageView(new Image(controller.table.player(0).playerDeck.cards.head.imgPath, cardSizeX, cardSizeY, true, false)))
                 this.setStyle("-fx-background-color: transparent")
                 effect = new Lighting
                 this.visible = true

               }

           )
         }
     }
   }

}
