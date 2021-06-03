package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.State
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.effect.Lighting
import scalafx.scene.image._
import scalafx.scene.layout.HBox

case class GUI (guiApp: GUIApp, controller: Controller) extends JFXApp {



   stage = new PrimaryStage {

     title = "Card Test"

     scene = new Scene(1320, 900) {
       val cardRatio = 0.25
       val cardSizeX = 500 * cardRatio
       val cardSizeY = 726 * cardRatio
       val img = new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX-10, cardSizeY-10, true, false)
       val view = new ImageView(img)

       content += new HBox {

         padding = Insets(300, 400, 750, 515)
         children = Seq(

           new Button() {
           this.setGraphic(view)
           this.setStyle("-fx-background-color: transparent")
           effect = new Lighting
           this.visible = true
           this.setLayoutX(0)
         },

         new Button() {
           this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX-10, cardSizeY-10, true, false)))
           this.setStyle("-fx-background-color: transparent")
           effect = new Lighting
           this.visible = true
           this.setLayoutX(0)
         }
         )
       }
       /*
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
       */
       val playerNumber = if (State.state=="Player1:") 1 else 0
       for (x <- 0 to controller.table.player(playerNumber).playerDeck.cards.size-1) {
         content += new HBox {
           padding = Insets(700 , 0, 0, 350 + x*120)
             children = Seq(
               new Button() {
                 this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX, cardSizeY, true, false)))
                 this.setStyle("-fx-background-color: transparent")
                 effect = new Lighting
                 this.visible = true
                 val cardNumber = x
                 onAction = () => (controller.throwCard(x + 1), guiApp.update ,println(controller.table.player(playerNumber).playerDeck.cards.size))
               }

           )
         }

       }

       for (x <- 0 to controller.table.player(if (State.state=="Player1:") 0 else 1).playerDeck.cards.size-1) {
         content += new HBox {
           padding = Insets(50 , 0, 0, 450 + x*80)
           children = Seq(
             new Button() {
               this.setGraphic(new ImageView(new Image(controller.table.player(if (State.state=="Player1:") 0 else 1).playerDeck.cards(x).imgPath, cardSizeX- 50, cardSizeY-50, true, false)))
               this.setStyle("-fx-background-color: transparent")
               effect = new Lighting
               this.visible = true
               val cardNumber = x

             }

           )
         }
         println(content(1).->(getChildren)._1)
         println(content(1).->(getChildren)._2(3))
       }
     }
   }

}
