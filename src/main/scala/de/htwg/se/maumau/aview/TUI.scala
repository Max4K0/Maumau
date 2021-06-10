package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.{Observer, State, nextPlayerEvent, winEvent}

import scala.io.StdIn.readLine
import scala.util.{Success, Try}

case class TUI(controller: Controller) extends Observer {
  controller.add(this)
  def processInputLine(input: String):String = {
    input.toString match {
      case "help" => println("throw card \n take card \n q = quit Game \n")
        "valid input"

      case "z" =>println("")
        if (State.state.equals("")) {
          println("you cant undo the start")
          "invalid undo"
        } else {
          println("|-----Undooooooo!-----|")
          controller.undo

          "valid undo"
        }

      case "r" =>println("")
        println(State.state)
        if (State.state.equals("")) {
          println("you cant redo the start")
          "invalid redo"
        } else {
          println("|-----reedoo!-----|")
          controller.redo
          "valid redo"
        }

      case "throw card" => println("wich card?")
        val cardNumber = readLine().toInt
        if (controller.checkCard(cardNumber)) {
          Try {controller.throwCard(cardNumber)} match {
//          case Failure(e) => println(e.getMessage)
//            "invalid throw"
            case Success(e) => print("commands: throw card, take card, q for Quit")
              "valid throw"
          }
          //println("\n\n")
          //println(controller)


        } else {
          //State.handle(invalidThrowEvent())
          println("you cant throw this card")
          "invalid throw"
        }

      case "change strat" => println("select strat")
        println("standard:  1")
        println("special:   2")
        val stratNumber = readLine().toInt

        stratNumber match {
            case 1 => print("you choose normal")
              controller.strategy = 1
              "valid strategy"
            case 2 => print("you choose special")
              controller.strategy = 2
              "valid strategy"
            case _ => print("no valid strategy")
              "invalid strategy"
          }

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
            case Success(e) => println("commands: throw card, take card, q for Quit")
              State.handle(nextPlayerEvent())
              println("")
              println(controller)
              "valid pull"
          }

        }
      case "q" =>
        State.handle(winEvent())

        "valid input"
      case _ => //State.handle(unknownCommandEvent())
        println("invalid command\ncommands: throw card, take card, q for Quit")

        "invalid input"
    }
  }


  override def update: Boolean = {

    println(controller)

    true
  }
}



