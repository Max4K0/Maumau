package de.htwg.se.maumau.util

trait GameState

case class invalidThrowEvent() extends GameState {
  def invalidThrow = println("you cant throw this card")
}

case class nextPlayerEvent() extends GameState {
  def nextPlayer = println("commands: throw card, take card, q for Quit")
}

case class invalidPullEvent() extends GameState {
  def invalidPull = println("you cant pull a card, cause you can throw one")
}

case class unknownCommandEvent() extends GameState {
  def unknownCommand = println("invalid command")
}

case class winEvent() extends GameState {
  def gameOver = println("the current player won!")
}

case class startEvent() extends GameState {
  def start = println("welcome!")
}