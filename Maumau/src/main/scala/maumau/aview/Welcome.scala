package maumau.aview

import maumau.controller.controllerComponent.ControllerInterface
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import maumau.util.Observer

class Welcome(controller: ControllerInterface){
  def welcome():String= {
    println("""|•♦♣♠♥•Welcome to MauMau!•♥♠♣♦•""".stripMargin)
    controller.addPlayer("P1", 0)
    controller.addPlayer("P2", 1)
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