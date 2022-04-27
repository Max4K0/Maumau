package fileIOComponent
import com.google.inject.Guice
import fileIOComponent.fileIO_Interface
import fileIOComponent.IOAPI
import scala.util.{Failure, Success, Try}

case object LaunchIOAPI {
  @main def launch =

    val injector = Guice.createInjector(new FileIOComponent)
    val controller = injector.getInstance(classOf[fileIO_Interface])
    Try(IOAPI) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)
}
