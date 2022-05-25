package fileIOComponent
import com.google.inject.AbstractModule
import databaseComponent.DatabaseInterface
import fileIOComponent.fileIO_Interface
import fileIOComponent.fileIO_JsonImpl.FileIO
import net.codingwell.scalaguice.ScalaModule
import databaseComponent.{DatabaseInterface, DatabaseImpl}


class FileIOComponent extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[fileIO_Interface]).to(classOf[fileIO_JsonImpl.FileIO])
    bind(classOf[DatabaseInterface]).to(classOf[DatabaseImpl])
  }
}
