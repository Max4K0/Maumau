package fileIOComponent.fileIO_JsonImpl
import fileIOComponent.fileIO_Interface
import play.api.libs.json.{JsValue, Json}

import java.io.*
import scala.io.Source
import java.net.URL
import java.nio.file.Paths

class FileIO extends fileIO_Interface{

  val file: File = new File("save.json")


  override def save(s: String): Unit =
    val pw = new PrintWriter(file)
    Some(pw.write(Json.prettyPrint(saveToJson(s))))
    pw.close()


  override def load(): String =
    val source = Source.fromFile(file)
    val sourceText: String = source.getLines.mkString
    println(sourceText)
    source.close()
    val json: JsValue = Json.parse(sourceText)
    Json.prettyPrint(Json.obj("themeManager" -> (json \ "themeManager").get))

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
      case e: Exception => throw e
    }
  }
}

