package de.htwg.se.maumau.util

trait GameState

case class invalidThrowEvent() extends GameState

case class nextPlayerEvent() extends GameState

case class invalidPullEvent() extends GameState

case class unknownCommandEvent() extends GameState

case class winEvent() extends GameState