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
          State.handle(nextPlayerEvent()).toString
        } else {
          State.handle(invalidThrowEvent()).toString
        }
      case "take card" =>
        if (controller.checkDeck()) {
          State.handle(invalidPullEvent()).toString
        }
        else {
          controller.takeCard()
          State.handle(nextPlayerEvent()).toString
        }
      case "q" => State.handle(winEvent()).toString
      case _ => State.handle(unknownCommandEvent()).toString
    }
  }
  override def update: Unit = println(controller)
}



