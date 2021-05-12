package de.htwg.se.maumau.model
import de.htwg.se.maumau.model._
import de.htwg.se.maumau.controller._
import de.htwg.se.maumau.model.Color._
import de.htwg.se.maumau.model.Symbol._

case class Card(color: Color, symbol: Symbols) {
  /**
   * UTF 8 Symbols and Colors for the Cards
   */
  val red = "\u001B[31m"
  val black = "\u001B[30m"
  val HA = red + "\uD83C\uDCB1"
  val H2 = red + "\uD83C\uDCB2"
  val H3 = red + "\uD83C\uDCB3"
  val H4 = red + "\uD83C\uDCB4"
  val H5 = red + "\uD83C\uDCB5"
  val H6 = red + "\uD83C\uDCB6"
  val H7 = red + "\uD83C\uDCB7"
  val H8 = red + "\uD83C\uDCB8"
  val H9 = red + "\uD83C\uDCB9"
  val H10 = red + "\uD83C\uDCBA"
  val HJ = red + "\uD83C\uDCBB"
  val HL = red + "\uD83C\uDCBD"
  val HK = red + "\uD83C\uDCBE"

  val SA = black + "\uD83C\uDCA1"
  val S2 = black + "\uD83C\uDCA2"
  val S3 = black + "\uD83C\uDCA3"
  val S4 = black + "\uD83C\uDCA4"
  val S5 = black + "\uD83C\uDCA5"
  val S6 = black + "\uD83C\uDCA6"
  val S7 = black + "\uD83C\uDCA7"
  val S8 = black + "\uD83C\uDCA8"
  val S9 = black + "\uD83C\uDCA9"
  val S10 = black + "\uD83C\uDCAA"
  val SJ = black + "\uD83C\uDCAB"
  val SL = black + "\uD83C\uDCAD"
  val SK = black + "\uD83C\uDCAE"

  val CA = red + "\uD83C\uDCC1"
  val C2 = red + "\uD83C\uDCC2"
  val C3 = red + "\uD83C\uDCC3"
  val C4 = red + "\uD83C\uDCC4"
  val C5 = red + "\uD83C\uDCC5"
  val C6 = red + "\uD83C\uDCC6"
  val C7 = red + "\uD83C\uDCC7"
  val C8 = red + "\uD83C\uDCC8"
  val C9 = red + "\uD83C\uDCC9"
  val C10 = red + "\uD83C\uDCCA"
  val CJ = red + "\uD83C\uDCCB"
  val CL = red + "\uD83C\uDCCD"
  val CK = red + "\uD83C\uDCCE"

  val DA = black + "\uD83C\uDCD1"
  val D2 = black + "\uD83C\uDCD2"
  val D3 = black + "\uD83C\uDCD3"
  val D4 = black + "\uD83C\uDCD4"
  val D5 = black + "\uD83C\uDCD5"
  val D6 = black + "\uD83C\uDCD6"
  val D7 = black + "\uD83C\uDCD7"
  val D8 = black + "\uD83C\uDCD8"
  val D9 = black + "\uD83C\uDCD9"
  val D10 = black + "\uD83C\uDCDA"
  val DJ = black + "\uD83C\uDCDB"
  val DL = black + "\uD83C\uDCDD"
  val DK = black + "\uD83C\uDCCE"

  override def toString: String = {
    symbol match {
      case Symbol.ASS => color.toString.charAt(0) + " A"
      case Symbol.Two => color.toString.charAt(0) + " 2"
      case Symbol.Three => color.toString.charAt(0) + " 3"
      case Symbol.Four => color.toString.charAt(0) + " 4"
      case Symbol.Five => color.toString.charAt(0) + " 5"
      case Symbol.Six => color.toString.charAt(0) + " 6"
      case Symbol.Seven => color.toString.charAt(0) + " 7"
      case Symbol.Eight => color.toString.charAt(0) + " 8"
      case Symbol.Nine => color.toString.charAt(0) + " 9"
      case Symbol.Ten => color.toString.charAt(0) + " 10"
      case Symbol.Jack => color.toString.charAt(0) + " J"
      case Symbol.Lady => color.toString.charAt(0) + " L"
      case Symbol.King => color.toString.charAt(0) + " K"
    }
  }

  def UTFSymbols: String = {
    symbol match {
      case Symbol.ASS if (color == Hearts) => HA
      case Symbol.Two if (color == Hearts) => H2
      case Symbol.Three if (color == Hearts) => H3
      case Symbol.Four if (color == Hearts) => H4
      case Symbol.Five if (color == Hearts) => H5
      case Symbol.Six if (color == Hearts) => H6
      case Symbol.Seven if (color == Hearts) => H7
      case Symbol.Eight if (color == Hearts) => H8
      case Symbol.Nine if (color == Hearts) => H9
      case Symbol.Ten if (color == Hearts) => H10
      case Symbol.Jack if (color == Hearts) => HJ
      case Symbol.Lady if (color == Hearts) => HL
      case Symbol.King if (color == Hearts) => HK

      case Symbol.ASS if (color == Spades) => SA
      case Symbol.Two if (color == Spades) => S2
      case Symbol.Three if (color == Spades) => S3
      case Symbol.Four if (color == Spades) => S4
      case Symbol.Five if (color == Spades) => S5
      case Symbol.Six if (color == Spades) => S6
      case Symbol.Seven if (color == Spades) => S7
      case Symbol.Eight if (color == Spades) => S8
      case Symbol.Nine if (color == Spades) => S9
      case Symbol.Ten if (color == Spades) => S10
      case Symbol.Jack if (color == Spades) => SJ
      case Symbol.Lady if (color == Spades) => SL
      case Symbol.King if (color == Spades) => SK

      case Symbol.ASS if (color == Clubs) => CA
      case Symbol.Two if (color == Clubs) => C2
      case Symbol.Three if (color == Clubs) => C3
      case Symbol.Four if (color == Clubs) => C4
      case Symbol.Five if (color == Clubs) => C5
      case Symbol.Six if (color == Clubs) => C6
      case Symbol.Seven if (color == Clubs) => C7
      case Symbol.Eight if (color == Clubs) => C8
      case Symbol.Nine if (color == Clubs) => C9
      case Symbol.Ten if (color == Clubs) => C10
      case Symbol.Jack if (color == Clubs) => CJ
      case Symbol.Lady if (color == Clubs) => CL
      case Symbol.King if (color == Clubs) => CK

      case Symbol.ASS if (color == Diamonds) => DA
      case Symbol.Two if (color == Diamonds) => D2
      case Symbol.Three if (color == Diamonds) => D3
      case Symbol.Four if (color == Diamonds) => D4
      case Symbol.Five if (color == Diamonds) => D5
      case Symbol.Six if (color == Diamonds) => D6
      case Symbol.Seven if (color == Diamonds) => D7
      case Symbol.Eight if (color == Diamonds) => D8
      case Symbol.Nine if (color == Diamonds) => D9
      case Symbol.Ten if (color == Diamonds) => D10
      case Symbol.Jack if (color == Diamonds) => DJ
      case Symbol.Lady if (color == Diamonds) => DL
      case Symbol.King if (color == Diamonds) => DK
    }
  }
}
