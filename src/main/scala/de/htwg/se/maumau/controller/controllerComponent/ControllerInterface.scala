package de.htwg.se.maumau.controller.controllerComponent
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{Table}
import de.htwg.se.maumau.util.{Observable}

import scala.collection.mutable.Stack

trait ControllerInterface extends Observable{

  def changeStrat(InStrat: Int): Unit
  def changeCheckCardLable(checkCard: Boolean): Unit
  def changeShouldUpdate(InshouldUpdate: Boolean): Unit
  def changeMainTheme(themeNumber: Int): Unit
  def changeCardTheme(themeNumber: Int): Unit
  def saveFile(): Unit
  def loadFile(): Unit
  def newGame(): Unit
  def changeVis(): Unit
  def changeThemeVis(): Unit
  def changeCardThemeVis(): Unit
  def visiblesettings:Boolean
  def visiblethememanager:Int
  def visiblecardthememanager:Int
  def table:Table
  def tables:Stack[Table]
  def states:Stack[String]
  def strategy:Int
  def shouldUpdate:Boolean
  def checkCardLable:Boolean
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

