package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.{Observer, State, invalidPullEvent, invalidThrowEvent, nextPlayerEvent, unknownCommandEvent, winEvent}
import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {
  controller.add(this)

  def processInputLine(input: String):String = {
    input.toString match {
      case "help" => println("throw card \n take card \n q = quit Game \n")
        "valid input"
      case "throw card" => println("wich card?")
        val cardNumber = readLine().toInt
        if (controller.checkCard(cardNumber)) {
          controller.throwCard(cardNumber)
          State.handle(nextPlayerEvent())
          println("commands: throw card, take card, q for Quit")
          "valid throw"
        } else {
          State.handle(invalidThrowEvent())
          println("you cant throw this card")
          "invalid throw"
        }
      case "take card" =>
        if (controller.checkDeck()) {
          State.handle(invalidPullEvent())
          println("you cant take a card").toString
          "invalid pull"
        }
        else {
          controller.takeCard()
          State.handle(nextPlayerEvent())
          println("commands: throw card, take card, q for Quit").toString
          "valid pull"
        }
      case "q" =>
        State.handle(winEvent())
        "valid input"
      case _ => State.handle(unknownCommandEvent())
        println("invalid command\ncommands: throw card, take card, q for Quit")
        "invalid input"
    }
  }
  override def update: Boolean = {
    println(controller)
    true
  }
}



