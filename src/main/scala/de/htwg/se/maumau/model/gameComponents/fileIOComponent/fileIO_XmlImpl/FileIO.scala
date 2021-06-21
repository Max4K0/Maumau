package de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_XmlImpl
import de.htwg.se.maumau.aview.{GUI, GUIApp}
import de.htwg.se.maumau.model.gameComponents.fileIOComponent.fileIO_Interface
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table

class FileIO extends fileIO_Interface{



  override def load(table: Table): Table = ???

  override def save(table: Table): Unit = ???

  override def loadTheme(theme: GUI): GUI = ???

  override def saveTheme(theme: GUI): Unit = ???
}
