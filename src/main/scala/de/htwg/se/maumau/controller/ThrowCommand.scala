package de.htwg.se.maumau.controller

import de.htwg.se.maumau.util.Command

class ThrowCommand (playerNumber: Int, cardNumber: Int, controller: Controller) extends Command  {

  override def doStep: Unit = controller.table = controller.table.throwCard(controller.table, playerNumber, cardNumber)


  override def undoStep: Unit = {
    controller.undoStep()
  }

  override def redoStep: Unit = {
    //controller.table = controller.table.takeCard(controller.table)
  }
}