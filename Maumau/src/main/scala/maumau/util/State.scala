package maumau.util

object State {
  var state = ""
  def handle(gs: GameState) = {
    gs match {
      //case a: invalidThrowEvent => state = invalidThrowEvent().invalidThrow
      case b: nextPlayerEvent => state = nextPlayerEvent().nextPlayer
      //case c: invalidPullEvent => state = invalidPullEvent().invalidPull
      //case d: unknownCommandEvent => state = unknownCommandEvent().unknownCommand
      case e: winEvent => state = winEvent().gameOver
    }
    state
  }

}