package fileIOComponent
import com.google.inject.AbstractModule
import fileIOComponent.fileIO_Interface
import fileIOComponent.fileIO_JsonImpl.FileIO
import net.codingwell.scalaguice.ScalaModule

class FileIOComponent extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[fileIO_Interface]).to(classOf[fileIO_JsonImpl.FileIO])
    //bind(classOf[fileIO_Interface]).to(classOf[fileIO_XmlImpl.FileIO])
  }
}
