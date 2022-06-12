package fileIOComponent.service

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{_string2NR as _, *}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode, HttpResponse}
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.google.inject.{Guice, Injector}
import fileIOComponent.{FileIOComponent, FileIOInterface}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}
import play.api.libs.json.JsValue

import sys.process.*
import scala.io.StdIn

object API:
  val injector: Injector = Guice.createInjector(new FileIOComponent)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])

  val connectIP = "localhost"
  val connectPort = sys.env.getOrElse("FILEIO_SERVICE_PORT", 8081).toString.toInt
  val routes: String =
    """
        Welcome to the Persistence REST service! Available routes:
          GET
          POST
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
      path("load") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, fileIO.load()))
      }
    },
    post {
      path("save") {
        entity(as[String]) { game =>
          fileIO.save(game) match
            case "200" => complete(HttpResponse(StatusCode.int2StatusCode(200), entity = "game saved"))
            case _ => complete(HttpResponse(StatusCode.int2StatusCode(500), entity = "game not saved"))
        }
      }
    }
  )
  val bindingFuture: Future[Http.ServerBinding] = Http().newServerAt(connectIP, connectPort).bind(route)
  bindingFuture.onComplete {
    case Success(binding) =>
      val address = binding.localAddress
      println(s"File IO REST service online at http://:${address.getPort}")

    case Failure(exception) => println("File IO REST service couldn't be started! Error: " + exception + "\n")
  }



