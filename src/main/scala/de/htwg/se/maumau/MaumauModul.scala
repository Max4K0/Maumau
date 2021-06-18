package de.htwg.se.maumau
import com.google.inject.name.Names
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table


class MaumauModul extends AbstractModule with ScalaModule{


  override def configure() = {
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
  }

}