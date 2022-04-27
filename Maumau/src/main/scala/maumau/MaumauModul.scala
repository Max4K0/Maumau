package maumau
import com.google.inject.AbstractModule
import maumau.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import net.codingwell.scalaguice.ScalaModule

class MaumauModul extends AbstractModule {

  //val default:Table = Table();

  override def configure(): Unit = {
    //bindConstant().annotatedWith(Names.named("default")).->(default)
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    //bind[fileIO_Interface].to[fileIO_JsonImpl.FileIO]
    //bind(classOf[fileIO_Interface]).to(classOf[fileIO_XmlImpl.FileIO])
  }
}
