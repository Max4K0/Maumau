package de.htwg.se.maumau.controller.controllerComponent
import de.htwg.se.maumau.model.gameBaseImpl.{TabelStrictStrategy, Table}
import de.htwg.se.maumau.util.{Observable, State, UndoManager}

import scala.collection.mutable.Stack

trait ControllerInterface extends Observable{

  def changeStrat(InStrat: Int): Unit
  def changeCheckCardLable(checkCard: Boolean): Unit
  def changeShouldUpdate(InshouldUpdate: Boolean): Unit
  def changeVis(): Boolean
  def visiblesettings:Boolean
  def table:Table
  def tables:Stack[Table]
  def states:Stack[String]
  def strategy:Int
  def shouldUpdate:Boolean
  def checkCardLable:Boolean
    //var commands = Stack[Comma]()

    def throwCard(cardNumber: Int): Unit
    def takeCard(): Unit
    def throwFirstCard(): Unit
    def checkCard(cardNumber: Int): Boolean
    def checkDeck(): Boolean
    def addPlayer(name: String, playerNum: Int): Unit
    def undoStep(): Unit
    def toString(): String
    def undo: Unit
    def redo: Unit
  }

