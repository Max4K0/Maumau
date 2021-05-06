package de.htwg.se.maumau.aview
import de.htwg.se.maumau.controller.Controller

import de.htwg.se.maumau.util.Observer

import scala.util.Random
import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {
  controller.add(this)

  def welcome():Unit= {
    val playerAmount: Int = readLine(
      """|•♦♣♠♥•Welcome to MauMau!•♥♠♣♦•
         |   Type a player number between 2-4: """.stripMargin).toInt

    val playrr:Unit = List.tabulate(playerAmount) {
      n => controller.addPlayer(readLine(s"Player ${n + 1}, type your name: "), n)
      }


  }


  def processInputLine(input: String):Unit = {
    input match {
      case "help" =>
        println("throw Card")
        println("take Card")
        println("q = quit Game")
      case "throw Card" => println("wich Card?")
        val cardNumber = readLine().toInt
        controller.throwCard(cardNumber)
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



