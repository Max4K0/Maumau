package maumau.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Inject
import maumau.controller.controllerComponent.ControllerInterface
import maumau.model.gameComponents.gameBaseImpl.{TabelStrictStrategy, Table}
import maumau.util.{State, UndoManager}
import maumau.MaumauModul
import com.google.inject.Guice

import scala.collection.mutable.Stack
import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.http.scaladsl.unmarshalling.Unmarshal
import play.api.libs.json.{JsValue, Json}
import scala.util.{Failure, Success}

import scala.concurrent.Future


class Controller @Inject()() extends ControllerInterface {
  var table = new Table()
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new MaumauModul)
  //val fileIo = injector.getInstance(classOf[fileIO_Interface])
  var tables = Stack[Table]()
  var states = Stack[String]("")
  val fileIOServer = "http://localhost:8081/fileio"
  var strategy = 1
  var shouldUpdate = true
  var checkCardLable = false
  var visiblesettings = false
  var visiblethememanager = 0
  var visiblecardthememanager = 0
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
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")

    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = fileIOServer + "/save",
      entity = Json.prettyPrint(Json.obj(
        "themeManager" -> Json.obj(
          "visibleCardThemeManager" -> this.visiblecardthememanager,
          "visibleThemeManager" -> this.visiblethememanager
        )
      ))))
    notifyObservers()
  }

  def loadFile(): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")

    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = fileIOServer + "/load"))

    responseFuture
      .onComplete {
        case Failure(_) => sys.error("Failed getting Json")
        case Success(value) => {
          Unmarshal(value.entity).to[String].onComplete {
            case Failure(_) => sys.error("Failed unmarshalling")
            case Success(value) => {
              val json = Json.parse(value)
              val theme = (json \ "themeManager").get
              this.visiblethememanager = (theme \ "visibleThemeManager").get.as[Int]
              this.visiblecardthememanager = (theme \ "visibleCardThemeManager").get.as[Int]
              notifyObservers()
            }
          }
        }
      }
    println("here")
    notifyObservers()
    //fileIo.load(this)
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------GUI Methods-----------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
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

  //--------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------Verification of Player Move-------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------------------
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
