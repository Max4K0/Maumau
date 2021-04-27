//package test
import de.htwg.se.maumau.model._
import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.matchers.should._
import scala.collection.mutable.ListBuffer
import de.htwg.se.maumau.model.Card
import scala.util.Random

import Color.Color
import Symbol.Symbols
case class Deck() {

  val rawDeck: ListBuffer[Card] = ListBuffer[Card](
    Card(Color.Cross, Symbol.ASS),
    Card(Color.Cross, Symbol.Two),
    Card(Color.Cross, Symbol.Three),
    Card(Color.Cross, Symbol.Four),
    Card(Color.Cross, Symbol.Five),
    Card(Color.Cross, Symbol.Six),
    Card(Color.Cross, Symbol.Seven),
    Card(Color.Cross, Symbol.Eight),
    Card(Color.Cross, Symbol.Nine),
    Card(Color.Cross, Symbol.Ten),
    Card(Color.Cross, Symbol.Jack),
    Card(Color.Cross, Symbol.Lady),
    Card(Color.Cross, Symbol.King),
    Card(Color.Spades, Symbol.ASS),
    Card(Color.Spades, Symbol.Two),
    Card(Color.Spades, Symbol.Three),
    Card(Color.Spades, Symbol.Four),
    Card(Color.Spades, Symbol.Five),
    Card(Color.Spades, Symbol.Six),
    Card(Color.Spades, Symbol.Seven),
    Card(Color.Spades, Symbol.Eight),
    Card(Color.Spades, Symbol.Nine),
    Card(Color.Spades, Symbol.Ten),
    Card(Color.Spades, Symbol.Jack),
    Card(Color.Spades, Symbol.Lady),
    Card(Color.Spades, Symbol.King),
    Card(Color.Diamonds, Symbol.ASS),
    Card(Color.Diamonds, Symbol.Two),
    Card(Color.Diamonds, Symbol.Three),
    Card(Color.Diamonds, Symbol.Four),
    Card(Color.Diamonds, Symbol.Five),
    Card(Color.Diamonds, Symbol.Six),
    Card(Color.Diamonds, Symbol.Seven),
    Card(Color.Diamonds, Symbol.Eight),
    Card(Color.Diamonds, Symbol.Nine),
    Card(Color.Diamonds, Symbol.Ten),
    Card(Color.Diamonds, Symbol.Jack),
    Card(Color.Diamonds, Symbol.Lady),
    Card(Color.Diamonds, Symbol.King),
    Card(Color.Heart, Symbol.ASS),
    Card(Color.Heart, Symbol.Two),
    Card(Color.Heart, Symbol.Three),
    Card(Color.Heart, Symbol.Four),
    Card(Color.Heart, Symbol.Five),
    Card(Color.Heart, Symbol.Six),
    Card(Color.Heart, Symbol.Seven),
    Card(Color.Heart, Symbol.Eight),
    Card(Color.Heart, Symbol.Nine),
    Card(Color.Heart, Symbol.Ten),
    Card(Color.Heart, Symbol.Jack),
    Card(Color.Heart, Symbol.Lady),
    Card(Color.Heart, Symbol.King))
  



  def shuffle () : ListBuffer[Card] = {
    val shuffledDeck =  Random.shuffle(rawDeck)
    shuffledDeck
  }

  def getCard (deck : ListBuffer[Card]): Card ={
    val card = deck.head
    val shuffledDeck = deck.drop(1)
    card
  }

}
val stdDeck = Deck()
print(stdDeck)
val shuffledDeck = stdDeck.shuffle()
print(shuffledDeck)
//val deck2 = Deck().getCard(shuffledDeck)
val card = Deck().getCard(shuffledDeck).toString
val shuffledDeck2 = shuffledDeck.drop(1)
print(shuffledDeck2)
print(shuffledDeck)


