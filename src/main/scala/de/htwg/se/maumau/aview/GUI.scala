package de.htwg.se.maumau.aview


import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.util.{State, winEvent}
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{JFXApp, Platform}
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.effect.Lighting
import scalafx.scene.image._
import scalafx.scene.layout.HBox

import scala.util.{Success, Try}
case class GUI (guiApp: GUIApp, controller: ControllerInterface) extends JFXApp {

  loop()

  def reprint() {
    stage = new PrimaryStage {

      title = "MauMau"

      this.getIcons.add(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/icon.png", 500, 500, true, true))

      onCloseRequest = (eventHandler) => {
        System.exit(0)
      }

      scene = new Scene(1320, 900) {

        val cardRatio = 0.25
        val cardSizeX = 500 * cardRatio
        val cardSizeY = 726 * cardRatio
        val table = new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/Table.png", 1000, 1000, false, false)
        val img = new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX - 10, cardSizeY - 10, true, false)
        val playerNumber = if (State.state == "Player1:") 1 else 0
        val view = new ImageView(img)
        val view2 = new ImageView(table)

        content = view2

        labels
        def labels: Unit = {
          content += new HBox {
            this.padding = Insets(0, 0, 0, 0)
            children = Seq(

              new Label() {
                this.padding = Insets(580, 0, 0, 545)
                this.text = "-You can not throw this card-"
                this.visible = controller.checkCardLable
              },

              new Label() {
                this.setStyle("-fx-font-size: 25")
                this.padding = Insets(400, 0, 0, -270)
                this.text = State.state
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size == 0 || controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size == 0

              }
            )
          }
        }
          player1Cards
          def player1Cards: Unit = {


            content += new HBox {
              children.addAll(new Button() {
                this.visible = false
              })
              this.padding = Insets(680, 0, 0, 635 - (45 * controller.table.player(playerNumber).playerDeck.cards.size))
              for (x <- 0 to controller.table.player(playerNumber).playerDeck.cards.size - 1) {
                children.addAll(new Button() {
                  this.padding = Insets(0, 0, 0, -30)
                  // this.padding = Insets(701 , 0, 500, -60)
                  //this.padding = Insets(700, 1, 1, (150 * x ) )
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0

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
                    if (controller.checkCard(x + 1)) {
                      Try {
                        controller.throwCard(x + 1)
                      } match {
                        case Success(e) => print("Success!\n")
                          controller.changeCheckCardLable(false)
                      }
                    } else {
                      /*
                      Invalid Card push Message
                       */

                      controller.changeCheckCardLable(true)
                      println("You cant throw this card")
                    }
                    //controller.throwCard(+ 1)
                    reprint()
                  }
                })
              }
            }

          }
        player2Cards
          def player2Cards: Unit = {
            for (x <- 0 to controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size - 1) {
              content += new HBox {

                padding = Insets(55, 0, 0, 642 + (x * 62) - (31 * controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size))
                children = Seq(
                  new Label() {
                    //this.setGraphic(new ImageView(new Image(controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards(x).imgPath, cardSizeX - 50, cardSizeY - 50, true, true)))
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 45, cardSizeY - 45, true, true)))
                    this.setStyle("-fx-background-color: transparent")
                    effect = new Lighting
                    this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0

                  }
                )
              }
            }
          }
        middleCards
          def middleCards: Unit = {

            content += new HBox {


              this.padding = Insets(300, 0, 0, 532)
              children = Seq(

                new Label() {
                  this.setGraphic(new ImageView(new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX - 10, cardSizeY - 10, true, true)))
                  this.setStyle("-fx-background-color: transparent")
                  effect = new Lighting
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0
                },

                new Label() {
                  this.padding = Insets(0, 0, 0, 20)
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                  this.setStyle("-fx-background-color: transparent")

                  this.onMouseEntered = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", (cardSizeX - 10) * 1.1, (cardSizeY - 10) * 1.1, true, true)))
                    this.padding = Insets(-5, 0, 0, 15)
                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                    this.padding = Insets(0, 0, 0, 20)
                  }

                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {

                    }
                    Try {
                      controller.takeCard()
                    } match {
                      //            case Failure(e) => println(e.getMessage)
                      //              "invalid try"
                      case Success(e) => println("Success!\n")
                        State.state = if (State.state == "Player1:") "Player2:" else "Player1:"
                        "valid pull"
                    }

                    reprint()
                  }
                  effect = new Lighting
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0

                }
              )
            }
          }

          undoredo
          def undoredo: Unit = {


            content += new HBox {


              this.padding = Insets(820, 0, 0, 0)
              children = Seq(

                new Label() {
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/settings.png", cardSizeX - 80, cardSizeY - 100, false, true)))
                  this.setStyle("-fx-background-color: transparent")
                  this.padding = Insets(0, 0, 0, 0)
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0

                  this.onMouseEntered = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/settings2.png", cardSizeX - 80, cardSizeY - 100, false, true)))

                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/settings.png", cardSizeX - 80, cardSizeY - 100, false, true)))

                  }

                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {

                    }
                    controller.changeVis()
                    reprint()
                  }
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0

                },


                new Label() {
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/left.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                  this.setStyle("-fx-background-color: transparent")
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0
                  this.padding = Insets(0, 0, 0, 0)
                  this.onMouseEntered = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/left.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                    this.padding = Insets(2, 0, 0, 0)
                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/left.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                    this.padding = Insets(0, 0, 0, 0)
                  }

                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {}
                    controller.undo
                    reprint()
                  }
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0 && controller.visiblesettings

                },

                new Label() {
                  this.padding = Insets(0, 0, 0, 0)
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/right.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                  this.setStyle("-fx-background-color: transparent")
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0

                  this.onMouseEntered = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/right.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                    this.padding = Insets(2, 0, 0, 0)


                  }
                  this.onMouseExited = (MouseEvent) => {
                    this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/right.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                    this.padding = Insets(0, 0, 0, 0)

                  }


                  this.onMouseClicked = (MouseEvent) => {
                    this.onMouseExited = (MouseEvent) => {}
                    controller.redo
                    reprint()
                  }
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0 && controller.visiblesettings

                }
              )
            }

          }
        }

      }
    }

    def loop(): Unit = {
      Platform.runLater(new Runnable() {
        if (controller.shouldUpdate) {
          controller.changeShouldUpdate(false)
          reprint()
        }

        override def run(): Unit = {
          loop()
        }

      })
    }

  }
