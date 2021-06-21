package de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_JsonImpl
import com.google.inject.Guice
import de.htwg.se.maumau.MaumauModul
import de.htwg.se.maumau.aview.GUI
import de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_Interface
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.{Card, Color, Symbol, Table}
import de.htwg.se.maumau.util.State
import play.api.libs.json.{JsString, JsValue, Json}

import scala.collection.mutable.ListBuffer
import scala.io.Source

class FileIO extends fileIO_Interface{


  override def load(table: Table): Table = {
    var table: Table = Table()
    val source: String = Source.fromFile("table.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new MaumauModul)

    val namesOfPlayer = (json \ "table" \ "namesOfPlayer").get.toString


    val state = (json \ "table" \ "state").get.toString

    var cards = new ListBuffer[Card]()
    for (color <- Color.values) {
      for (value <- Symbol.values) {
        cards += Card(color, value)
      }
    }
    table
  }


  def tableToJson(table: Table): JsValue = {
    Json.obj(
      "table" -> Json.obj(
        "nameOfPlayer1" -> JsString(table.player(0).name),
        "nameOfPlayer2" -> JsString(table.player(1).name),
        "state" -> JsString(State.state),
      )
    )
  }

  override def save(grid: Table): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("table.json"))
    pw.write(Json.prettyPrint(tableToJson(grid)))
    pw.close()
  }


  def themeToJson(gui: GUI): JsValue = ???
  //{
//    Json.obj(
//      "table" -> Json.obj(
//        "tableTheme" -> JsString(GUI.),
//        "nameOfPlayer2" -> JsString(table.player(1).name),
//        "state" -> JsString(State.state),
//      )
//    )
  //}

  override def loadTheme(theme: GUI): GUI = ???

  override def saveTheme(theme: GUI): Unit = ???


}

