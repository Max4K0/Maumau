package de.htwg.se.maumau.controller

import de.htwg.se.maumau.util.Command

class PullCommand(playerNumber : Int, controller: Controller) extends Command  {
  override def doStep: Unit = controller.table = controller.table.takeCard(controller.table, playerNumber)


  override def undoStep: Unit = {
    controller.undoStep()
  }

  override def redoStep: Unit = {
     //controller.table = controller.table.takeCard(controller.table)
  }
}