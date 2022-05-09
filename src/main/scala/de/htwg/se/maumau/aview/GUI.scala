package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.util.State
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.application.{JFXApp3, Platform}
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.effect.Lighting
import scalafx.scene.image._
import scalafx.scene.layout.HBox

import scala.collection.Seq
import scala.language.implicitConversions
import scala.util.{Success, Try}

case class GUI (guiApp: GUIApp, controller: ControllerInterface) extends JFXApp3 {

  def start(): Unit = {
      if (controller.shouldUpdate) {
        controller.changeShouldUpdate(false)
        reprint()
      }
  }

  def reprint(): Unit = {

    stage = new JFXApp3.PrimaryStage {

      title.value = "MauMau"
      //--------------------------------------------------------------------------------------------------------------------------------
      //------------------------------------------------------------Gui Icon------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      this.getIcons.add(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/icon.png", 500, 500, true, true))

      //--------------------------------------------------------------------------------------------------------------------------------
      //-----------------------------------------------------------Exit + Save----------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      onCloseRequest = (eventHandler) => {
        controller.saveFile()
        System.exit(0)
      }
      //--------------------------------------------------------------------------------------------------------------------------------
      //----------------------------------------------------Creating Scene + Variables--------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      scene = new Scene(1320, 900) {
        val cardRatio = 0.25
        val cardSizeX = 500 * cardRatio
        val cardSizeY = 726 * cardRatio
        val table = new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/Table.png", 1000, 1000, false, true)
        val img = new Image(controller.table.tableDecks(1).cards.last.imgPath, cardSizeX - 10, cardSizeY - 10, false, false)
        val playerNumber = if (State.state == "Player1:") 1 else 0
        val view = new ImageView(img)
        val view2 = new ImageView(table)
        view2.setStyle("-fx-background-color: transparent")
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
                this.padding = Insets(400, 0, 0, -289)
                this.text = State.state
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size == 0 || controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size == 0

              }

            )
          }
        }
        //------------------------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------Player 1-----------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------
        player1Cards

        def player1Cards: Unit = {
          content += new HBox {
            children.addAll(new Button() {
              this.visible = false
            })
            this.padding = Insets(680, 0, 0, 635 - (45 * controller.table.player(playerNumber).playerDeck.cards.size))
            //--------------------------------------
            // Creating a variable amount of Buttons
            //--------------------------------------
            for (x <- 0 to controller.table.player(playerNumber).playerDeck.cards.size - 1) {
              children.addAll(new Button() {
                this.padding = Insets(0, 0, 0, -30)
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0

                //-------------------------------------
                // Card Image and Effects
                //-------------------------------------
                this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX, cardSizeY, true, true)))
                this.setStyle("-fx-background-color: transparent")
                effect = new Lighting

                //-------------------------------------
                // Animations and Actions
                //-------------------------------------
                this.onMouseEntered = (MouseEvent) => {
                  this.padding = Insets(-50, 0, 500, -30)
                  this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX * 1.1, cardSizeY * 1.1, true, true)))
                }
                this.onMouseExited = (MouseEvent) => {
                  this.padding = Insets(0, 0, 0, -30)
                  this.setGraphic(new ImageView(new Image(controller.table.player(playerNumber).playerDeck.cards(x).imgPath, cardSizeX, cardSizeY, true, true)))
                }

                //-------------------------------------
                // Card Push + Verification
                //-------------------------------------
                this.onMouseClicked = (MouseEvent) => {
                  this.onMouseExited = (MouseEvent) => {}
                  if (controller.checkCard(x + 1)) {
                    Try {
                      controller.throwCard(x + 1)
                    } match {
                      case Success(e) => print("Success!\n")
                        controller.changeCheckCardLable(false)
                    }
                  } else {
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

        //------------------------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------Player 2-----------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------
        player2Cards

        def player2Cards: Unit = {
          for (x <- 0 to controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size - 1) {
            content += new HBox {
              padding = Insets(55, 0, 0, 642 + (x * 62) - (31 * controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size))
              children = Seq(
                new Label() {
                  //-------------------------------------
                  // Theme Manager
                  //-------------------------------------
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 45, cardSizeY - 45, true, true)))
                  //-------------------------------------
                  // Card Effects
                  //-------------------------------------
                  this.setStyle("-fx-background-color: transparent")
                  effect = new Lighting
                  this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0
                }
              )
            }
          }
        }
        //------------------------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------Stack Cards-----------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------
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

                //-------------------------------------
                // Alignment
                //-------------------------------------
                this.padding = Insets(0, 0, 0, 20)
                //-------------------------------------
                // Theme Manager
                //-------------------------------------
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                //-------------------------------------
                // Card Effects
                //-------------------------------------
                this.setStyle("-fx-background-color: transparent")
                effect = new Lighting
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0
                //-------------------------------------
                // Animations and Actions
                //-------------------------------------
                this.onMouseEntered = (MouseEvent) => {
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", (cardSizeX - 10) * 1.1, (cardSizeY - 10) * 1.1, true, true)))
                  this.padding = Insets(-5, 0, 0, 15)
                }
                this.onMouseExited = (MouseEvent) => {
                  this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/red_back2.png", cardSizeX - 10, cardSizeY - 10, true, true)))
                  this.padding = Insets(0, 0, 0, 20)
                }
                //-------------------------------------
                // Card Pull + Verification
                //-------------------------------------
                this.onMouseClicked = (MouseEvent) => {
                  this.onMouseExited = (MouseEvent) => {
                  }
                  Try {
                    controller.takeCard()
                  } match {
                    case Success(e) => println("Success!\n")
                      State.state = if (State.state == "Player1:") "Player2:" else "Player1:"
                      "valid pull"
                  }
                  //----------
                  //Update Gui
                  //----------
                  reprint()
                }


              }
            )
          }
        }


        //------------------------------------------------------------------------------------------------------------------------------
        //----------------------------------------------------------Setting-------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------
        settings

        def settings: Unit = {
          content += new HBox {
            this.padding = Insets(820, 0, 0, 0)
            children = Seq(

              new Label() {
                //-------------------------------------
                // Setting Image and Effects
                //-------------------------------------
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/settings.png", cardSizeX - 80, cardSizeY - 100, false, true)))
                this.setStyle("-fx-background-color: transparent")
                this.padding = Insets(0, 0, 0, 0)
                //-------------------------------------
                // Animations and Actions
                //-------------------------------------
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

              //------------------------------------------------------------------------------------------------------------------------------
              //--------------------------------------------------------Undo Button-----------------------------------------------------------
              //------------------------------------------------------------------------------------------------------------------------------
              new Label() {
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/left.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                this.setStyle("-fx-background-color: transparent")
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

              //------------------------------------------------------------------------------------------------------------------------------
              //--------------------------------------------------------Redo Button-----------------------------------------------------------
              //------------------------------------------------------------------------------------------------------------------------------
              new Label() {
                this.padding = Insets(0, 0, 0, 0)
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/right.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                this.setStyle("-fx-background-color: transparent")

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
              },

              //------------------------------------------------------------------------------------------------------------------------------
              //----------------------------------------------------Theme Manager Button------------------------------------------------------
              //------------------------------------------------------------------------------------------------------------------------------
              new Label() {
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/thememanager.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                this.setStyle("-fx-background-color: transparent")
                this.padding = Insets(0, 0, 0, 0)
                this.onMouseEntered = (MouseEvent) => {
                  this.padding = Insets(2, 0, 0, 0)
                }
                this.onMouseExited = (MouseEvent) => {
                  this.padding = Insets(0, 0, 0, 0)
                }

                this.onMouseClicked = (MouseEvent) => {
                  this.onMouseExited = (MouseEvent) => {}
                  controller.changeThemeVis()
                  reprint()
                }
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0 && controller.visiblesettings
              },

              //------------------------------------------------------------------------------------------------------------------------------
              //-----------------------------------------------------Card Manager Button------------------------------------------------------
              //------------------------------------------------------------------------------------------------------------------------------
              new Label() {
                this.setGraphic(new ImageView(new Image("file:src/main/scala/de/htwg/se/maumau/util/textures/cardthememanager.png", cardSizeX - 80, cardSizeY - 80, false, true)))
                this.setStyle("-fx-background-color: transparent")
                this.padding = Insets(0, 0, 0, 0)
                this.onMouseEntered = (MouseEvent) => {
                  this.padding = Insets(2, 0, 0, 0)
                }
                this.onMouseExited = (MouseEvent) => {
                  this.padding = Insets(0, 0, 0, 0)
                }

                this.onMouseClicked = (MouseEvent) => {
                  this.onMouseExited = (MouseEvent) => {}
                  controller.changeCardThemeVis()
                  reprint()
                }
                this.visible = controller.table.player(playerNumber).playerDeck.cards.size != 0 && controller.table.player(if (State.state == "Player1:") 0 else 1).playerDeck.cards.size != 0 && controller.visiblesettings

              }
            )
          }

        }
      }

    }

    //------------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------Gui Update------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------

  }
}
