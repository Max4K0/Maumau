package fileIOComponent.service
import com.google.inject.Guice
import databaseComponent.DatabaseInterface
import fileIOComponent.FileIOInterface
import fileIOComponent.service.API

import scala.util.{Failure, Success, Try}
import fileIOComponent.FileIOComponent

case object LaunchAPI {
  @main
  def launch(): Unit =
    val injector = Guice.createInjector(new FileIOComponent)
    val controller = injector.getInstance(classOf[FileIOInterface])
    val database = injector.getInstance(classOf[DatabaseInterface])
    database.createDB()
    Try(API) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)
}



