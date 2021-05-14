import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.{Observer, State, invalidPullEvent, invalidThrowEvent, nextPlayerEvent, unknownCommandEvent, winEvent}
import scala.io.StdIn.readLine
import de.htwg.se.maumau.model.{Card, Color, Deck, Symbol, Table}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.util.winEvent

import scala.util.Random

val testdeck = Deck().fillDeck.shuffleDeck(new Random(3))
val card1 = Card(Color.Clubs, Symbol.Five)
val testdeck2 = Deck()
print(testdeck2.cards.map(Card => Card.symbol).contains(card1.symbol))
State.handle(nextPlayerEvent())
State.handle(unknownCommandEvent())
print(State.state)

var numberlist = List[Int](1,2)
print(numberlist)