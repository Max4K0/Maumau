package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.Observer

class Welcome(controller: Controller){
  def welcome():String= {//<--String
    println("""|•♦♣♠♥•Welcome to MauMau!•♥♠♣♦•""".stripMargin)
    controller.addPlayer("P1", 0)// Unit ->
    controller.throwFirstCard()
    //    val playerAmount: Int = readLine(
    //      """|•♦♣♠♥•Welcome to MauMau!•♥♠♣♦•
    //         |   Type a player number between 2-4: """.stripMargin).toInt
    //
    //    val playrr:Unit = List.tabulate(playerAmount) {
    //      n => controller.addPlayer(readLine(s"Player ${n + 1}, type your name: "), n)
    //      }
    "game start successfully"
  }
}