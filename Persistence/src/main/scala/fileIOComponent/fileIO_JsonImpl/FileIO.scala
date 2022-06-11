package fileIOComponent.fileIO_JsonImpl

import fileIOComponent.{FileIOComponent, FileIOInterface}
import play.api.libs.json.{JsValue, Json}
import com.google.inject.{Guice, Inject, Injector}

import java.io.*
import scala.io.Source
import scala.io.*
import databaseComponent.*

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

class FileIO extends FileIOInterface {

  val injector = Guice.createInjector(new FileIOComponent)
  val database = injector.getInstance(classOf[DatabaseInterface])

  override def save(gameState: String): Unit = {
    println("saving")
    Try({
      database.writeTable(gameState)
    }) match {
      case Success(_) => println("success")
      case Failure(exception) => println(exception)
    }
  }

  override def load(): String = {
    database.readTable()
  }


/*
  val file = new File("save.json")

  override def save(gameState: String): Unit = {
    println(gameState)
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
*/
}

