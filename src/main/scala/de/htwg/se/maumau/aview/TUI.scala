package de.htwg.se.maumau.aview
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.{Card, Deck, Player, Table, Symbol, Color} //muss weg
import de.htwg.se.maumau.util.Observer

import scala.util.Random
import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {

  controller.add(this)
  val startDeck = Deck(List[Card]()).fillDeck.shuffleDeck(new Random(3))

  def gamestart(): Unit = {
    val playerAmount: Int = readLine(
      """||==== Welcome to MauMau! ====|
         ||   How many players want to play?  |
         ||   Type a number between 2-4: """.stripMargin).toInt
    if (!controller.testPlayeramount(playerAmount)) return



    val player: List[Player] = List.tabulate(playerAmount) {
      n => Player(readLine(s"Player ${n + 1}, type your name: "), Deck(List[Card]()))
    }
    start(player)
  }
  def start(players: List[Player]): Unit = {



    val tableDeck = Deck(List[Card]()) //Ablegestapel
    startDeck.throwCards(1, tableDeck)
    while (true) {
      for (player <- players) {


        val  usableCards = if ((player.playerDeck.cards.contains(tableDeck.cards.head.symbol) ||
            player.playerDeck.cards.contains(tableDeck.cards.head.color)) ||
            player.playerDeck.cards.contains(Symbol.Jack)) true else false
        var validMove = false
        val playerCardNr = readLine("choose a valid Card to throw").toInt
        do {

            validMove = if ((player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == tableDeck.cards.head.symbol) ||
            (player.playerDeck.cards.lift(playerCardNr - 1).get.color == tableDeck.cards.head.color) ||
            player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == Symbol.Jack) true else false
        }while (validMove == false)


        player.playerDeck.throwOneCard(playerCardNr, tableDeck)

        if (player.playerDeck.cards.equals(List[Card]())) print(player.name, "Won") false
      }
    }
  }
  override def update: Unit = ???
}
  /*
##############

    H  L

dfsdfdsaffsdfs
------------------------
player1: choose a card

*/



