package de.htwg.se.maumau.util

import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import de.htwg.se.maumau.model.{Card, Deck, Player, Table}
import de.htwg.se.maumau.util.State
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.util.{Observer, State, nextPlayerEvent, winEvent}

import java.io.ByteArrayInputStream
import scala.util.Random

class StateSpec extends AnyWordSpec with Matchers{

  "State" when {
    "Game starts" should {
      "state after start should be" in {
        State.state= ""
        State.state should be("")
      }
//      "state after invalid throw should be" in {
//        State.handle(invalidThrowEvent())
//        State.state should be("you cant throw this card")
//      }
      "state after next Player should be" in {
        State.handle(nextPlayerEvent())
        State.state should be("Player1:")
        State.handle(nextPlayerEvent())
        State.state should be("Player2:")
      }
      //"state after invalid pull event should be" in {
      //  State.handle(invalidPullEvent())
      //  State.state should be("you cant pull a card, cause you can throw one")
      //}
      //"state after wrong Input should be" in {
      //  State.handle(unknownCommandEvent())
      //  State.state should be("invalid command")
      //}

    }

  }

}
