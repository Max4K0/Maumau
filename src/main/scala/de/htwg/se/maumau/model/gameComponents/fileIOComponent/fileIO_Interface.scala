package de.htwg.se.maumau.model.gameComponents.fileIOComponent

import de.htwg.se.maumau.controller.controllerComponent.ControllerInterface

trait fileIO_Interface {

  def load(controller: ControllerInterface): Unit
  def save(controller: ControllerInterface): Unit
}
