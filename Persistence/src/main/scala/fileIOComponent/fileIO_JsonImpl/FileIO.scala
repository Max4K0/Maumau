package fileIOComponent.fileIO_JsonImpl

import fileIOComponent.fileIO_Interface
import play.api.libs.json.{JsValue, Json}

import java.io.*
import scala.io.Source
import scala.io.*

class FileIO extends fileIO_Interface{

  val file = new File("save.json")

  override def save(gameState: String): Unit = {
    val pw = new PrintWriter(new File("save.json"))
    pw.write(gameState)
    pw.close()
  }


  override def load(): String = {
    val source: BufferedSource = Source.fromFile("save.json")
    val gameState: String = source.getLines.mkString
    source.close()
    gameState
  }
}

