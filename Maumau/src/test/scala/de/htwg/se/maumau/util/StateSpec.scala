package maumau.util

import maumau.aview.{TUI, Welcome}
import maumau.controller.controllerComponent.controllerBaseImpl.Controller
import maumau.util.State
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import maumau.util.{Observer, State, nextPlayerEvent, winEvent}

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
      "state a Player 1 win should be" in {
        State.state= "Player2:"
        State.handle(winEvent())
        State.state should be("♦♥♠♣--Player 1 won!--♣♠♥♦")
      }
      "state a Player 2 win should be" in {
        State.state= "Player1:"
        State.handle(winEvent())
        State.state should be("♦♥♠♣--Player 2 won!--♣♠♥♦")
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
