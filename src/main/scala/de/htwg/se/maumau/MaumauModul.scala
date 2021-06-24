package de.htwg.se.maumau
import com.google.inject.AbstractModule
import de.htwg.se.maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.maumau.model.gameComponents.fileIOComponent._

class MaumauModul extends AbstractModule with ScalaModule{

  //val default:Table = Table();

  override def configure() = {
    //bindConstant().annotatedWith(Names.named("default")).->(default)
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
  }

}
