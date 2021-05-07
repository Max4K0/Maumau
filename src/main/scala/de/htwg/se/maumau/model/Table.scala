package de.htwg.se.maumau.model
import de.htwg.se.maumau.model.{Player, Deck, Card}
case class Table(player: List[Player] = List[Player](Player("P1", Deck()), Player("P2", Deck())), tableDecks: List[Deck] = List[Deck](Deck().fillDeck,Deck(List[Card]()))) {

//  override def toString: String = {
//    println(tableDeck.cards.head)
//    for(playerDeck.cards <- Card)
//    mkString() <_ Playerdeck.cards
//    }
//  }


  def addPlayers(table: Table, name: String, playerNumber: Int): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwCards(5, emptyDeck)
    val newPlayer = Player(name, changedDeck._1)
    val newDeck = changedDeck._2
    val newTable = table.copy(player = table.player.updated(playerNumber, newPlayer), tableDecks.updated(0, newDeck))
    newTable
  }

  def throwFirstCard(table: Table): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwOneCard(1, emptyDeck)
    val newTable = table.copy(player = player,tableDecks.updated(1, changedDeck._1).updated(0, changedDeck._2))
    newTable
  }

  def throwCard(table: Table, playerNumber: Int, cardNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = currentPlayer.playerDeck.throwOneCard(cardNumber, tableDecks(1))
    val newPlayer = Player(currentPlayer.name,changedDeck._2)
    val newTable = table.copy(player.updated(0, newPlayer), tableDecks.updated(1,changedDeck._1))
    newTable
  }

  override def toString: String = {
    val table = new StringBuilder(" tablecards: ")
    table.append(tableDecks(1).cards.last.UTFSymbols)

    val hand = new StringBuilder(" playercards: ")
    hand.append(player.head.playerDeck.cards.map(Card => Card.UTFSymbols).mkString(" "))

    val Statement = new StringBuilder()
    Statement.append(table + "\n\n")
    Statement.append(hand)
    Statement.toString()
  }
//  def gamestart(): Unit = {
//
//    if (!testPlayeramount(playerAmount)) return
//
//    def testPlayeramount(amount: Int): Boolean = {
//      amount match {
//        case 2 | 3 | 4 => true
//        case _ => false
//      }
//    }
//
//    val player: List[Player] = List.tabulate(playerAmount) {
//      n => Player(readLine(s"Player ${n + 1}, type your name: "), Deck(List[Card]()))
//    }
//
//    val startDeck = Deck(List[Card]()).fillDeck.shuffleDeck(new Random(3))
//
//    def start(players: List[Player], table: Table): Unit = {
//      for (player <- players) {
//        startDeck.throwCards(5, player.playerDeck)
//      }
//      val tableDeck = Deck(List[Card]())
//      startDeck.throwCards(1, tableDeck)
//      while (true) {
//
//
//        for (player <- players) {
//          print(table)
//
//          val  usableCards = if ((player.playerDeck.cards.contains(tableDeck.cards.head.symbol) ||
//            player.playerDeck.cards.contains(tableDeck.cards.head.color)) ||
//            player.playerDeck.cards.contains(Symbol.Jack)) true else false
//          var validMove = false
//
//
//          do {
//            val playerCardNr = readLine("choose a valid Card to throw").toInt
//            validMove = if ((player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == tableDeck.cards.head.symbol) ||
//              (player.playerDeck.cards.lift(playerCardNr - 1).get.color == tableDeck.cards.head.color) ||
//              player.playerDeck.cards.lift(playerCardNr - 1).get.symbol == Symbol.Jack) true else false
//          }while (validMove == false)
//
//          if (player.playerDeck.cards.equals(List[Card]())) print(player.name, "Won") false
//        }
//
//
//
//      }
//    }
//
//  }



}
/*
Das gesagte in Textform:
  -TUI bekommt eine API vom Controller (Dieser beantwortet es dann)
  -Model ist beliebig änderbar
  -Controller hat eine Schnittstelle für die TUI
  -interface, damit die TUI ihre fragen beantwortet kriegt
  -mainloop in TUI soll in die Main
  -nur ein Println. Alles andere soll Stringverarbeitung sein
  -Start ist String
  -Model soll wie ein Tree aufgebaut sein
  -Wir erzeugen ein Game, das wir dem Controller übergeben. Dann übergeben wir den Controller der TUI. (Saubere struktur)

  ++-Controller hat eine var, die mutable ist. Das ist auch der Wurzelknoten. (z.b game, table oder board) (nicht die main oder TUI)
  ++-derzeit ist unser Wurzelknoten Table (Zeile 10 Controller.scala)
*/
