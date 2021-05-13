package de.htwg.se.maumau.util

object State {
  var state = println("")
  def handle(gs: GameState) = {
    gs match {
      case a: invalidThrowEvent => state = invalidThrowEvent
      case b: nextPlayerEvent => state = nextPlayerEvent
      case c: invalidPullEvent => state = invalidPullEvent
      case d: unknownCommandEvent => state = unknownCommandEvent
      case e: winEvent => state = winEvent
      case f: startEvent => state = startEvent
    }
    state
  }
  def invalidThrow = println("you cant throw this card")
  def nextPlayer = println("commands: throw card, take card, q for Quit")
  def invalidPull = println("you cant pull a card, cause you can throw one")
  def unknownCommand = println("invalid command")
  def gameOver = println("the current player won!")
  def start = println("welcome!")
}