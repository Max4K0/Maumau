import de.htwg.se.maumau.Maumau
import de.htwg.se.maumau.Maumau.{controller, welcome}
import de.htwg.se.maumau.model.{Card, Color, Deck, Symbol, Table}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.winEvent

import scala.util.Random

val testdeck = Deck().fillDeck.shuffleDeck(new Random(3))
val card1 = Card(Color.Clubs, Symbol.Five)
val testdeck2 = Deck()
print(testdeck2.cards.map(Card => Card.symbol).contains(card1.symbol))