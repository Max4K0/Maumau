package de.htwg.se.maumau.model
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.Card
import de.htwg.se.maumau.util.Observer

import scala.io.StdIn.readLine

case class Rules(playerCard: Card, tabel: Card) extends Observer{
  override def update: Unit = ???
}

