package de.htwg.se.maumau.aview
import de.htwg.se.maumau.controller.Controller

import de.htwg.se.maumau.util.Observer

import scala.util.Random
import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {
  controller.add(this)




  def processInputLine(input: String):String = {
    input.toString match {
      case "help" => println("throw card \n take card \n q = quit Game \n")
        "valid input"
      case "throw card" => println("wich card?")
        "valid input"
      case "1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"|"10"|"11"|"12" => println("throwed " + input + "card")
        val cardNumber = input.toInt
        controller.throwCard(cardNumber)
        "valid input"
      case "q" =>println("gg")
        "valid exit"
      case _ => println("invalid input")
        println("try again")
        "invalid input"
    }
  }
//  def gamestart(): Unit = {
//
//    if (!testPlayeramount(playerAmount)) return
//
//    def testPlayeramount(amount: Int): Boolean = {
//      amount match {
//        case 2 | 3 | 4 => true
//        case _ => false
//      }
//    }
//
//    val player: List[Player] = List.tabulate(playerAmount) {
//      n => Player(readLine(s"Player ${n + 1}, type your name: "), Deck(List[Card]()))
//    }
//
//  }

  override def update: Unit = println(controller)
}



