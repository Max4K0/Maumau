package maumau.aview

import maumau.controller.controllerComponent.ControllerInterface
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import maumau.util.Observer
import scala.io.StdIn.readLine

class Welcome(controller: ControllerInterface){
  def welcome():String= {
    println("""|•♦♣♠♥•Welcome to MauMau!•♥♠♣♦•""".stripMargin)

    println("Load last game? (Y/n)")
    var input : String = ""
    while (input == "") {
      input = readLine()
    }
    if(input == "Y" | input == "y" | input == "yes") {
      try {
        controller.loadFile()
        println("Loaded last Game!")
      }catch{
        case e : Exception => System.exit(1)
      }
    } else {
      controller.newGame()
      println("Starting new Game!")
      controller.addPlayer("P1", 0)
      println("added player 1")
      controller.addPlayer("P2", 1)
      println("added player 2")
      controller.throwFirstCard()
    }
    "game start successfully"
  }
}