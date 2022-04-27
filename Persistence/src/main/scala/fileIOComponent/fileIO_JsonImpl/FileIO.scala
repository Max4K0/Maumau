package fileIOComponent.fileIO_JsonImpl
import fileIOComponent.fileIO_Interface
import play.api.libs.json.{JsValue, Json}

import java.io.*
import scala.io.Source

class FileIO extends fileIO_Interface{

  val file = new File("save.json")

  override def save(s: String): Unit = {
    val pw = new PrintWriter(new File("save.json"))
    Some(pw.write(Json.prettyPrint(saveToJson(s))))
    pw.close()
  }


  override def load(): JsValue = {
    val source: String = Source.fromFile("save.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    //val visibleCardThemeManager = (json \ "visibleCardThemeManager").get
    //val visibleThemeManager = (json \ "visibleThemeManager").get
    Json.obj("themeManager" -> (json \ "themeManager").get)
  }

  def saveToJson(s: String): JsValue = {
    val json = Json.parse(s)
    try{
      Json.obj(
        "themeManager" -> Json.obj(
          "visibleThemeManager" -> (json \ "visibleThemeManager").get,
          "visibleCardThemeManager" -> (json \ "visibleCardThemeManager").get,
        )
      )
    }catch{
      case e: Exception => throw IOException()
    }
  }
}

