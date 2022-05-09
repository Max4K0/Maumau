package de.htwg.se.maumau
import com.google.inject.AbstractModule
import de.htwg.se.maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import fileIOComponent.{fileIO_Interface, fileIO_JsonImpl}
import net.codingwell.scalaguice.ScalaModule

class MaumauModul extends AbstractModule {

  override def configure() = {
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])

    bind(classOf[fileIO_Interface]).to(classOf[fileIO_JsonImpl.FileIO])
  }
}
