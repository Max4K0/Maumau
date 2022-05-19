package de.htwg.se.maumau.aview

import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.maumau.util.Observer

import scala.io.StdIn.readLine

class Welcome(controller: ControllerInterface){
  def welcome():String= {
    println("Load last game?")
    var input = ""
    input = readLine()
    if(input == "y") {
      try {
        controller.loadFile()
        println("Loaded last Game!")
        "game start successfully"
      } catch {
        case e: Throwable => throw e
      }
    } else {
      controller.newGame()
      println("Starting new Game!")
      controller.addPlayer("P1", 0)
      println("added player 1")
      controller.addPlayer("P2", 1)
      println("added player 2")
      controller.throwFirstCard()
      "game start successfully"
    }

  }
}