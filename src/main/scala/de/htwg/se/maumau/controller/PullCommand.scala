package de.htwg.se.maumau.controller

import de.htwg.se.maumau.util.Command

class PullCommand(controller: Controller) extends Command  {
  override def doStep: Unit = {
   // controller.table.player = controller.table.takeCard(controller.table)
  }

  override def undoStep: Unit = {
   // controller.table.player = controller.table.undo(controller.table)
  }

  override def redoStep: Unit = {
    // controller.table.player = controller.table.takeCard(controller.table)
  }
}