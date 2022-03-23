package de.htwg.se.maumau
import com.google.inject.AbstractModule
import de.htwg.se.maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.maumau.model.gameComponents.fileIOComponent._

class MaumauModul extends AbstractModule {

  //val default:Table = Table();

  override def configure() = {
    //bindConstant().annotatedWith(Names.named("default")).->(default)
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    //bind[fileIO_Interface].to[fileIO_JsonImpl.FileIO]
    bind(classOf[fileIO_Interface]).to(classOf[fileIO_XmlImpl.FileIO])
  }
}
