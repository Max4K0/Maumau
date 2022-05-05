package maumau.aview

import maumau.controller.controllerComponent.ControllerInterface
import maumau.util.{Observer, State, nextPlayerEvent, winEvent}

import scala.io.StdIn.readLine
import scala.util.{Success, Try}

case class TUI(controller: ControllerInterface) extends Observer {
  controller.add(this)

  //--------------------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------------Process Input Line------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def processInputLine(input: String):String = {
    input.toString match {

      //--------------------------------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------HELP!------------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "help" => println("Commands       Description\n" +
        "throw card   => You throw a card\n" +
        "take card    => You take a card\n" +
        "change strat => Chance the Strategy\n" +
        "undo         => Go one step back\n" +
        "redo         => Go one step forward\n" +
        "quit         => Quit the game")
        "valid input"

      //--------------------------------------------------------------------------------------------------------------------------------
      //----------------------------------------------------------Undo------------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "undo" =>println("")
        if (State.state.equals("")) {
          println("You cannot use undo at the beginning of the game!")
          "invalid undo"
        } else {
          println("<-<-<-Undooo!<-<-<-")
          controller.undo()
          "valid undo"
        }
      //--------------------------------------------------------------------------------------------------------------------------------
      //----------------------------------------------------------Redo------------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "redo" =>println("")
        println(State.state)
        if (State.state.equals("")) {
          println("You are already at the latest position!")
          "invalid redo"
        } else {
          println("->->->Redooo!->->->")
          controller.redo()
          "valid redo"
        }

      //--------------------------------------------------------------------------------------------------------------------------------
      //-------------------------------------------------------Push Card----------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "throw card" => println("Wich card?")
        val cardNumber = readLine().toInt
        if (controller.checkCard(cardNumber)) {
          Try {controller.throwCard(cardNumber)} match {
//          case Failure(e) => println(e.getMessage)
//            "invalid throw"
            case Success(e) => print("Success!\n")
              "valid throw"
          }
        } else {
          println("You cant throw this card")
          "invalid throw"
        }
      //--------------------------------------------------------------------------------------------------------------------------------
      //----------------------------------------------------Change Strategy-------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "change strat" => println("Select strat")
        println("Type 1 for the default settings")
        println("Type 2 for the special settings")
        val stratNumber = readLine().toInt

        stratNumber match {
            case 1 => print("you choose normal")
              controller.changeStrat(1)
              "valid strategy"
            case 2 => print("you choose special")
              controller.changeStrat(2)
              "valid strategy"
            case _ => print("no valid strategy")
              "invalid strategy"
          }

      //--------------------------------------------------------------------------------------------------------------------------------
      //-------------------------------------------------------Pull Card----------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "take card" =>
        if (controller.checkDeck()) {
          //State.handle(invalidPullEvent())
          println("you cant take a card").toString
          "invalid pull"
        }
        else {
          Try {controller.takeCard()} match {
//            case Failure(e) => println(e.getMessage)
//              "invalid try"
            case Success(e) => println("Success!\n")
              State.handle(nextPlayerEvent())
              println("")
              println(controller)
              "valid pull"
          }

        }
      //--------------------------------------------------------------------------------------------------------------------------------
      //-------------------------------------------------------Quit---------------------------------------------------------------------
      //--------------------------------------------------------------------------------------------------------------------------------
      case "quit" =>
        State.handle(winEvent())
        controller.saveFile()
        "valid input"
      case _ => //State.handle(unknownCommandEvent())
        println("Invalid command! Type help to see all commands.")
        "invalid input"
    }
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------------Controller update-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  override def update: Boolean = {
    println(controller)
    true
  }
}



