package de.htwg.se.maumau.util

object State {
  var state = println()
  def handle(gs: GameState) = {
    gs match {
      case a: invalidThrowEvent => state = invalidThrow
      case b: nextPlayerEvent => state = nextPlayer
      case c: invalidPullEvent => state = invalidPull
      case d: unknownCommandEvent => state = unknownCommand
      case e: winEvent => state = gameOver
    }
    state
  }

  def invalidThrow = println("you cant throw this card");
  def nextPlayer = println("next Player");
  def invalidPull = println("you cant pull a card, cause you can throw one");
  def unknownCommand = println("invalid command");
  def gameOver = println("the current player won!");

}