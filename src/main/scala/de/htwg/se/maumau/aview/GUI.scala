package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.State
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{JFXApp, Platform}
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.effect.Lighting
import scalafx.scene.image._
import scalafx.scene.layout.HBox
case class GUI (guiApp: GUIApp, controller: Controller) extends JFXApp {

  loop()
  def reprint() {
        stage = new PrimaryStage {

          title = "MauMau"



          onCloseRequest = (eventHandler) =>{
            System.exit(1)
          }

          scene = new Scene(1320, 900) {


            val cardRatio = 0.25
            val cardSizeX = 500 * cardRatio
            val cardSizeY = 726 * cardRatio
            val table = new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/Table.png", 1000, 1000, false, false)
            val img = new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX - 10, cardSizeY - 10, true, false)

            val view = new ImageView(img)
            val view2 = new ImageView(table)

            content += view2

            content = view2



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

            /*
        * Player 1 Cards
        * */

            val playerNumber = if (State.state == "Player1:") 1 else 0

            content += new HBox {
              children.addAll(new Button() {
                this.visible = false
              })
              this.padding = Insets(680, 0, 0, 400)
              for (x <- 0 to controller.table.player(playerNumber).playerDeck.cards.size - 1) {
                children.addAll(new Button() {
                  this.padding = Insets(0, 0, 0, -30)
                  // this.padding = Insets(701 , 0, 500, -60)
                  //this.padding = Insets(700, 1, 1, (150 * x ) )
                  this.visible = true
                  //
                  this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX, cardSizeY, true, true)))
                  this.setStyle("-fx-background-color: transparent")
                  effect = new Lighting
                  //
                  val cardNumber = 1
                  this.onMouseEntered = (MouseEvent) => {
                    this.padding = Insets(-50, 0, 500, -30)
                    this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX * 1.1, cardSizeY * 1.1, true, true)))
                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.padding = Insets(0, 0, 0, -30)
                    this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX, cardSizeY, true, true)))
                  }
                  //
                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {
                      //
                    }
                    controller.throwCard(x + 1)
                    reprint()
                    println(x + 1)
                  }
                })
                //
              }
            }
            //}
            //  println(content(1).->(getChildren)._1.getEffect)


            /*
        * Player 2 Cards
        * */

            for (x <- 0 to controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size - 1) {
              content += new HBox {
                padding = Insets(50, 0, 0, 455 + x * 80)
                children = Seq(
                  new Label() {
                    //this.setGraphic(new ImageView(new Image(controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards(x).imgPath, cardSizeX - 50, cardSizeY - 50, true, true)))
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 50, cardSizeY - 50, true, true)))
                    this.setStyle("-fx-background-color: transparent")
                    effect = new Lighting
                    this.visible = true

                  }
                )
              }
            }
            content += new HBox {


              this.padding = Insets(300, 0, 0, 532)
              children = Seq(

                new Label() {
                  this.setGraphic(new ImageView(new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX - 10, cardSizeY - 10, true, true)))
                  this.setStyle("-fx-background-color: transparent")
                  effect = new Lighting
                  this.visible = true

                },

                new Label() {
                  this.padding = Insets(0, 0, 0, 20)
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                  this.setStyle("-fx-background-color: transparent")

                  this.onMouseEntered = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX * 1.1, cardSizeY * 1.1, true, true)))

                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                  }

                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {
                    }
                    controller.takeCard()
                    State.state = if (State.state == "Player1:") "Player2:" else "Player1:"
                    reprint()
                  }
                  effect = new Lighting
                  this.visible = true

                }
              )
            }
          }

        }
      }
      def loop(): Unit = {
        Platform.runLater(new Runnable(){
          if (controller.shouldUpdate) {
            controller.shouldUpdate = false
            reprint()
          }
        override def run(): Unit = {
          loop()
        }

    });
        }

}
