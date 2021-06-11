package de.htwg.se.maumau.util

trait GameState

//case class invalidThrowEvent() extends GameState {
//  def invalidThrow = "you cant throw this card"
//}

case class nextPlayerEvent() extends GameState {
  def nextPlayer = if (State.state == "Player1:") "Player2:" else "Player1:"
}

//case class invalidPullEvent() extends GameState {
//  def invalidPull = "you cant pull a card, cause you can throw one"
//}

//case class unknownCommandEvent() extends GameState {
//  def unknownCommand = "invalid command"
//}

case class winEvent() extends GameState {
  def gameOver  = if (State.state == "Player1:") "♦♥♠♣--Player 2 won!--♣♠♥♦" else "♦♥♠♣--Player 1 won!--♣♠♥♦"
}
