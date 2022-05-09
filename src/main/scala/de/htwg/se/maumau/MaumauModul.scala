package de.htwg.se.maumau
import com.google.inject.AbstractModule
import de.htwg.se.maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import net.codingwell.scalaguice.ScalaModule

class MaumauModul extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
  }
}
