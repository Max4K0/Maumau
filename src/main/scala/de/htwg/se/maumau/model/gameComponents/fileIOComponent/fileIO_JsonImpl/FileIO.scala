package de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_JsonImpl
import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface
import de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_Interface
import play.api.libs.json.{JsValue, Json}

import java.io._
import scala.io.Source

class FileIO extends fileIO_Interface{

  val file = new File("save.json")


  override def save(controller: ControllerInterface): Unit = {

    val pw = new PrintWriter(new File("save.json"))
    pw.write(Json.prettyPrint(saveToJson(controller)))
    pw.close()
  }


  override def load(controller: ControllerInterface): Unit = {
//    var table: Table = Table()
    val source: String = Source.fromFile("save.json").getLines.mkString
    val json: JsValue = Json.parse(source)
//    val injector = Guice.createInjector(new MaumauModul)
    val visableCardThemeManager = (json \ "themeManager" \ "visableCardThemeManager").get
    val visableThemeManager = (json \ "themeManager" \ "visableThemeManager").get

    print(visableCardThemeManager)
    print(visableThemeManager)
    controller.changeCardTheme(visableCardThemeManager.as[Int])
    controller.changeMainTheme(visableThemeManager.as[Int])
  }

  def saveToJson(controller: ControllerInterface): JsValue = {
    Json.obj(
      "themeManager" -> Json.obj(
        "visableThemeManager" -> controller.visiblethememanager,
        "visableCardThemeManager" -> controller.visiblecardthememanager,
      )
    )
  }
}

