package de.htwg.se.maumau.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
