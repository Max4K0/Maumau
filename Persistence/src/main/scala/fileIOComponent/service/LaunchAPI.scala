package fileIOComponent.service
import com.google.inject.Guice
import fileIOComponent.fileIO_Interface
import fileIOComponent.service.API

import scala.util.{Failure, Success, Try}
import fileIOComponent.FileIOComponent

import scala.io.StdIn

case object LaunchAPI {
  @main def launch(): Unit =
    Try(API) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)
  StdIn.readLine()
}

