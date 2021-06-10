package de.htwg.se.maumau.controller


import de.htwg.se.maumau.model.{TabelStrictStrategy, Table}
import de.htwg.se.maumau.util.{Observable, State, UndoManager}

import scala.collection.mutable.Stack

class Controller(var table: Table) extends Observable {

  private val undoManager = new UndoManager
  var tables = Stack[Table]()
  var states = Stack[String]("")
  var strategy = 1
  var shouldUpdate = true
  var checkCardLable = false
 //var commands = Stack[Comma]()

  def throwCard(cardNumber: Int): Unit = {
    tables.push(table)
    states.push(State.state)
    val playerNumber = if (State.state == "Player1:") 1 else 0
    //table = table.throwCard(table, playerNumber, cardNumber)
    undoManager.doStep(new ThrowCommand(playerNumber, cardNumber, this))
    notifyObservers()
    shouldUpdate = true
  }
  def takeCard(): Unit = {
    tables.push(table)
    states.push(State.state)
    val playerNumber = if (State.state == "Player1:") 1 else 0
    //table = table.takeCard(table, playerNumber)
    undoManager.doStep(new PullCommand(playerNumber, this))
    //commands.push(playerNumber, this)
    notifyObservers()
    shouldUpdate = true
  }

  def throwFirstCard(): Unit = {
    table = table.throwFirstCard(table)
    notifyObservers()
  }
  def checkCard(cardNumber: Int): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    strategy match {
      case 1 => table.checkCard(table, playerNumber, cardNumber)
      case 2 => (new TabelStrictStrategy).checkCard(table, playerNumber, cardNumber)
    }
  }

  def checkDeck(): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    strategy match {
      case 2 => table.checkDeck(table, playerNumber)
      case 1 => (new TabelStrictStrategy).checkDeck(table, playerNumber)
    }
  }

  def addPlayer(name: String, playerNum: Int): Unit = {
    table = table.addPlayers(table, name, playerNum)
    tables.push(table)
    states.push(State.state)
   // table = (new TabelStrictStrategy).addPlayers(table, name, playerNum)
  }
  def undoStep(): Unit = {
    table = tables.pop()
    State.state = states.pop()
    // table = (new TabelStrictStrategy).addPlayers(table, name, playerNum)
  }

  override def toString(): String = {
    table.toString()
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers()
    shouldUpdate = true
  }
  def redo: Unit = {
    undoManager.redoStep
    notifyObservers()
    shouldUpdate = true
  }
}
