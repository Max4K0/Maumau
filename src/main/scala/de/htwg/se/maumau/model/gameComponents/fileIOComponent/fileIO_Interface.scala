package de.htwg.se.maumau.model.gameComponents.fileIOComponent

import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Table
import de.htwg.se.maumau.aview.GUI

trait fileIO_Interface {

  def load(table: Table): Table
  def save(table: Table): Unit
  def loadTheme(theme: GUI): GUI
  def saveTheme(theme: GUI): Unit
}
