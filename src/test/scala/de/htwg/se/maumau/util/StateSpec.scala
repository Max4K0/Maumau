package de.htwg.se.maumau.util

import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.{Card, Deck, Player, Table}
import de.htwg.se.maumau.util.State
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.util.{Observer, State, invalidThrowEvent, nextPlayerEvent, winEvent}

import java.io.ByteArrayInputStream
import scala.util.Random

class StateSpec extends AnyWordSpec with Matchers{

  "State" when {
    "Game starts" should {
      val table = Table(List[Player](Player("P1", Deck()), Player("P2", Deck())), List[Deck](Deck().fillDeck.shuffleDeck(new Random(2)),Deck(List[Card]())))
      val controller = new Controller(table)
      val welcome = new Welcome(controller)
      val tui = TUI(controller)
      welcome.welcome()
      "state after start should be" in {
        val in = new ByteArrayInputStream("3".getBytes)
        Console.withIn(in) { tui.processInputLine("throw card") }
        State should be(State)
      }
      "state after exit should be" in {
        tui.processInputLine("q")
        State should be(State)
      }
      "state after wrong Input should be" in {
        tui.processInputLine("wdadwa")
        State should be(State)
      }
     "state after invalid Throw should be" in {
       val in = new ByteArrayInputStream("1".getBytes)
       Console.withIn(in) { tui.processInputLine("throw card") }
       State should be(State)
     }
     "state after wrong pull should be" in {
       tui.processInputLine("take card")
       State should be(State)
     }


    }
    "Game2 starts" should {
      val table2 = Table(List[Player](Player("P1", Deck()), Player("P2", Deck())), List[Deck](Deck().fillDeck.shuffleDeck(new Random(4)),Deck(List[Card]())))
      val controller2 = new Controller(table2)
      val welcome2 = new Welcome(controller2)
      val tui = TUI(controller2)
      welcome2.welcome()
      "state2 after wrong pull should be" in {
        tui.processInputLine("take card")
        State should be(State)
      }


    }

  }

}
