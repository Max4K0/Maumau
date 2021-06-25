package de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Inject
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{TabelStrictStrategy, Table}
import de.htwg.se.maumau.util.{State, UndoManager}
import de.htwg.se.maumau.MaumauModul
import com.google.inject.Guice
import de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_Interface
import scala.collection.mutable.Stack

class Controller @Inject()() extends ControllerInterface {
  var table = new Table()
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new MaumauModul)
  val fileIo = injector.getInstance(classOf[fileIO_Interface])
  var tables = Stack[Table]()
  var states = Stack[String]("")
  var strategy = 1
  var shouldUpdate = true
  var checkCardLable = false
  var visiblesettings = false
  var visiblethememanager = 0
  var visiblecardthememanager = 0
  //var commands = Stack[Comma]()


  def changeStrat(InStrat: Int): Unit = {
    strategy = InStrat
  }

  def saveFile(): Unit = {
    fileIo.save(this)
  }

  def loadFile(): Unit = {
    fileIo.load(this)
  }

  def changeVis(): Unit = {
    if (visiblesettings == false) visiblesettings = true else visiblesettings = false
    visiblesettings
  }

  def changeMainTheme(themeNumber: Int): Unit = {
    visiblethememanager = themeNumber
  }

  def changeCardTheme(themeNumber: Int): Unit = {
    visiblecardthememanager = themeNumber
  }

  def changeThemeVis(): Unit = {
    visiblethememanager match {

      case 0 => {
        visiblethememanager += 1
      }

      case 1 => {
        visiblethememanager += 1
      }

      case 2 => {
        visiblethememanager = 0
      }
    }
  }
  def changeCardThemeVis(): Unit = {
    visiblecardthememanager match {

      case 0 => {
        visiblecardthememanager += 1
      }

      case 1 => {
        visiblecardthememanager = 0
      }


    }
  }

  def changeCheckCardLable(checkCard: Boolean): Unit = {
    checkCardLable = checkCard
  }

  def changeShouldUpdate(InshouldUpdate: Boolean): Unit = {
    shouldUpdate = InshouldUpdate
  }



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
