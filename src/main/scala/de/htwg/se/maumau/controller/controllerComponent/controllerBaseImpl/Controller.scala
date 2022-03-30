package de.htwg.se.maumau.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Inject
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{Table, TableStrictStrategy}
import de.htwg.se.maumau.util.{State, UndoManager}
import de.htwg.se.maumau.MaumauModul
import com.google.inject.Guice
import de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_Interface

import scala.collection.mutable
import scala.collection.mutable.Stack

class Controller @Inject()() extends ControllerInterface {
  var table = new Table()
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new MaumauModul)
  val fileIo = injector.getInstance(classOf[fileIO_Interface])
  val tables = mutable.Stack[Table]()
  val states = mutable.Stack[String]("")
  var strategy = 1
  var shouldUpdate = true
  var checkCardLable = false
  var visibleSetting = false
  var visibleThemeManager = 0
  var visibleCardThemeManager = 0
  //var commands = Stack[Comma]()

  //--------------------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------------Strategy Methods--------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def changeStrat(InStrat: Int): Unit = {
    strategy = InStrat
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------File IO Methods------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def saveFile(): Unit = {
    fileIo.save(this)
  }

  def loadFile(): Unit = {
    fileIo.load(this)
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------GUI Methods-----------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def changeVis(): Unit = {
    if (visibleSetting == false) visibleSetting = true else visibleSetting = false
    visibleSetting
  }

  def changeMainTheme(themeNumber: Int): Unit = {
    visibleThemeManager = themeNumber
  }

  def changeCardTheme(themeNumber: Int): Unit = {
    visibleCardThemeManager = themeNumber
  }

  def changeThemeVis(): Unit = {
    visibleThemeManager match {

      case 0 => {
        visibleThemeManager += 1
      }

      case 1 => {
        visibleThemeManager += 1
      }

      case 2 => {
        visibleThemeManager = 0
      }
    }
  }
  def changeCardThemeVis(): Unit = {
    visibleCardThemeManager match {

      case 0 => {
        visibleCardThemeManager += 1
      }

      case 1 => {
        visibleCardThemeManager = 0
      }
    }
  }
  def changeCheckCardLable(checkCard: Boolean): Unit = {
    checkCardLable = checkCard
  }
  def changeShouldUpdate(InshouldUpdate: Boolean): Unit = {
    shouldUpdate = InshouldUpdate
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------Verification of Player Move-------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def checkCard(cardNumber: Int): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    strategy match {
      case 1 => table.checkCard(table, playerNumber, cardNumber)
      case 2 => (new TableStrictStrategy).checkCard(table, playerNumber, cardNumber)
    }
  }

  def checkDeck(): Boolean = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    strategy match {
      case 2 => table.checkDeck(table, playerNumber)
      case 1 => (new TableStrictStrategy).checkDeck(table, playerNumber)
    }
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------Player move after verification------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
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

  //--------------------------------------------------------------------------------------------------------------------------------
  //---------------------------------------------------Dealing the Cards------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def throwFirstCard(): Unit = {
    table = table.throwFirstCard(table)
    notifyObservers()
  }


  //--------------------------------------------------------------------------------------------------------------------------------
  //------------------------------------------------------Startseq.-----------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def addPlayer(name: String, playerNum: Int): Unit = {
    table = table.addPlayers(table, name, playerNum)
    tables.push(table)
    states.push(State.state)
   // table = (new TabelStrictStrategy).addPlayers(table, name, playerNum)
  }
  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------Undo a. Redo----------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  def undoStep(): Unit = {
    table = tables.pop()
    State.state = states.pop()
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
  //--------------------------------------------------------------------------------------------------------------------------------
  //------------------------------------------------------to String-----------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
  override def toString(): String = {
    table.toString()
  }
}
