package fileIOComponent.service

import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{_string2NR => _, _}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.google.inject.{Guice, Injector}
import fileIOComponent.{FileIOComponent, FileIOInterface}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}
import play.api.libs.json.JsValue

import sys.process._
import scala.io.StdIn

object API:
  val injector: Injector = Guice.createInjector(new FileIOComponent)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
  val routes: String =
    """
        Welcome to the Persistence REST service! Available routes:
          GET   /fileio/load
          POST  /fileio/save
          POST  /fileio/quit
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
    path("fileio" / "load") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, fileIO.load()))
      }
    },
    path("fileio" / "save") {
      post {
        entity(as[String]) { game =>
          fileIO.save(game)
          complete("game saved")
        }
      }
    }
  )
  val bindingFuture: Future[Http.ServerBinding] = Http().newServerAt("0.0.0.0", 8081).bind(route)

  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

