package de.htwg.se.maumau.aview
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.{Card, Deck, Player, Table}
import de.htwg.se.maumau.util.Observer

import scala.util.Random
import scala.io.StdIn.readLine

case class TUI(contoller: Controller) extends Observer {

  contoller.add(this)
  val startDeck = Deck(List[Card]()).fillDeck.shuffleDeck(new Random(3))
  def gamestart(): Unit = {
    val playerAmount: Int = readLine(
      """||==== Welcome to MauMau! ====|
         ||   How many players want to play?  |
         ||   Type a number between 2-4: """.stripMargin).toInt
    if (!testPlayeramount(playerAmount)) return

    def testPlayeramount(amount: Int): Boolean = {
      amount match {
        case 2 | 3 | 4 => true
        case _ => false
      }
    }
    val table = Table(startDeck)
    val player: List[Player] = List.tabulate(playerAmount) {
      n => Player(readLine(s"Player ${n + 1}, type your name: "), Deck(List[Card]()))
    }
    start(player, table)
  }
  def start(players: List[Player], table: Table): Unit = {
    for(player <- players) {
      startDeck.throwCards(5, player.playerDeck)
    }
    val tableDeck = Deck(List[Card]())
    startDeck.throwCards(1, tableDeck)
    while(true) {
      for(player <- players) {

      }
    }
  }

  override def update: Unit = ???
}
