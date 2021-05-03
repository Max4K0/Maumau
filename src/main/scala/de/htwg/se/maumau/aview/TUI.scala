package de.htwg.se.maumau.aview
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.{Card, Deck, Player, Table, Symbol, Color}
import de.htwg.se.maumau.util.Observer

import scala.util.Random
import scala.io.StdIn.readLine

case class TUI(contoller: Controller) extends Observer {

  contoller.add(this)
  val startDeck = Deck(List[Card]()).fillDeck.shuffleDeck(new Random(3))
  val tableDeck = Deck(List[Card]())
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
    val table = Table(startDeck, tableDeck)
    val player: List[Player] = List.tabulate(playerAmount) {
      n => Player(readLine(s"Player ${n + 1}, type your name: "), Deck(List[Card]()))
    }
    start(player, table)
  }
  def start(players: List[Player], table: Table): Unit = {
    for (player <- players) {
      startDeck.throwCards(5, player.playerDeck)
    }
    val tableDeck = Deck(List[Card]())
    startDeck.throwCards(1, tableDeck)
    while (true) {
      for (player <- players) {
        print(table)

        val  usableCards = if ((player.playerDeck.cards.contains(tableDeck.cards.head.symbol) ||
            player.playerDeck.cards.contains(tableDeck.cards.head.color)) ||
            player.playerDeck.cards.contains(Symbol.Jack)) true else false
        var validMove = false

        do {
          val playerCardNr = readLine("choose a valid Card to throw").toInt
            validMove = if ((player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == tableDeck.cards.head.symbol) ||
            (player.playerDeck.cards.lift(playerCardNr - 1).get.color == tableDeck.cards.head.color) ||
            player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == Symbol.Jack) true else false
        }while (validMove == false)

        if (player.playerDeck.cards.equals(List[Card]())) print(player.name, "Won") false
      }
    }
  }
  override def update: Unit = ???
}
