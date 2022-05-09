package fileIOComponent.service

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}
import fileIOComponent.fileIO_JsonImpl.FileIO
import play.api.libs.json.JsValue


object API :

  val fileIO = new FileIO

  val routes: String =
    """
        Welcome to the Persistence REST service! Available routes:
          GET   /fileio/load
          POST  /fileio/save
        """.stripMargin

  // needed to run the route
  val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
  given ActorSystem[Any] = system
  // needed for the future flatMap/onComplete in the end
  val executionContext: ExecutionContextExecutor = system.executionContext
  given ExecutionContextExecutor = executionContext

  val route: Route = concat(

    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
    },
    get {
      path("fileio" / "load") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, fileIO.load()))
      }
    },
    post {
      path("fileio" / "save") {
        entity(as [String]) { game =>
          fileIO.save(game)
          complete("game saved")
        }
      }
    }
  )

  val bindingFuture: Future[Http.ServerBinding] = Http().newServerAt("0.0.0.0", 8081).bind(route)

  bindingFuture.onComplete{
    case Success(binding) => {
      val address = binding.localAddress
      println(s"File IO REST service online at http://localhost:${address.getPort}\nPress RETURN to stop...")
    }
    case Failure(exception) => {
      println("File IO REST service couldn't be started! Error: " + exception + "\n")
    }
  }

