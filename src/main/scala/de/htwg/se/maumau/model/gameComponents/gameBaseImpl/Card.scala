package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Color._
import de.htwg.se.maumau.model.gameComponents.gameBaseImpl.Symbol._

case class Card(color: Color, symbol: Symbols) {
  /**
   * UTF 8 Symbols and Colors for the Cards
   */
  val red = "\u001B[31m"
  val red2 = "\u001B[38;5;124m"
  val black2 = "\u001B[38;5;20m"
  val black = "\u001B[30m"
  val HA = red2 + "\uD83C\uDCB1"
  val H2 = red2 + "\uD83C\uDCB2"
  val H3 = red2 + "\uD83C\uDCB3"
  val H4 = red2 + "\uD83C\uDCB4"
  val H5 = red2 + "\uD83C\uDCB5"
  val H6 = red2 + "\uD83C\uDCB6"
  val H7 = red2 + "\uD83C\uDCB7"
  val H8 = red2 + "\uD83C\uDCB8"
  val H9 = red2 + "\uD83C\uDCB9"
  val H10 = red2 + "\uD83C\uDCBA"
  val HJ = red2 + "\uD83C\uDCBB"
  val HL = red2 + "\uD83C\uDCBD"
  val HK = red2 + "\uD83C\uDCBE"

  val SA = black2 + "\uD83C\uDCA1"
  val S2 = black2 + "\uD83C\uDCA2"
  val S3 = black2 + "\uD83C\uDCA3"
  val S4 = black2 + "\uD83C\uDCA4"
  val S5 = black2 + "\uD83C\uDCA5"
  val S6 = black2 + "\uD83C\uDCA6"
  val S7 = black2 + "\uD83C\uDCA7"
  val S8 = black2 + "\uD83C\uDCA8"
  val S9 = black2 + "\uD83C\uDCA9"
  val S10 = black2 + "\uD83C\uDCAA"
  val SJ = black2 + "\uD83C\uDCAB"
  val SL = black2 + "\uD83C\uDCAD"
  val SK = black2 + "\uD83C\uDCAE"

  val DA = red + "\uD83C\uDCC1"
  val D2 = red + "\uD83C\uDCC2"
  val D3 = red + "\uD83C\uDCC3"
  val D4 = red + "\uD83C\uDCC4"
  val D5 = red + "\uD83C\uDCC5"
  val D6 = red + "\uD83C\uDCC6"
  val D7 = red + "\uD83C\uDCC7"
  val D8 = red + "\uD83C\uDCC8"
  val D9 = red + "\uD83C\uDCC9"
  val D10 = red + "\uD83C\uDCCA"
  val DJ = red + "\uD83C\uDCCB"
  val DL = red + "\uD83C\uDCCD"
  val DK = red + "\uD83C\uDCCE"

  val CA = black + "\uD83C\uDCD1"
  val C2 = black + "\uD83C\uDCD2"
  val C3 = black + "\uD83C\uDCD3"
  val C4 = black + "\uD83C\uDCD4"
  val C5 = black + "\uD83C\uDCD5"
  val C6 = black + "\uD83C\uDCD6"
  val C7 = black + "\uD83C\uDCD7"
  val C8 = black + "\uD83C\uDCD8"
  val C9 = black + "\uD83C\uDCD9"
  val C10 = black + "\uD83C\uDCDA"
  val CJ = black + "\uD83C\uDCDB"
  val CL = black + "\uD83C\uDCDD"
  val CK = black + "\uD83C\uDCDE"

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

  def imgPath: String = {
    symbol match {
      case Symbol.ASS if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/ace_of_hearts.png"
      case Symbol.Two if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/2_of_hearts.png"
      case Symbol.Three if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/3_of_hearts.png"
      case Symbol.Four if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/4_of_hearts.png"
      case Symbol.Five if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/5_of_hearts.png"
      case Symbol.Six if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/6_of_hearts.png"
      case Symbol.Seven if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/7_of_hearts.png"
      case Symbol.Eight if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/8_of_hearts.png"
      case Symbol.Nine if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/9_of_hearts.png"
      case Symbol.Ten if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/10_of_hearts.png"
      case Symbol.Jack if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/jack_of_hearts.png"
      case Symbol.Lady if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/queen_of_hearts.png"
      case Symbol.King if (color == Hearts) => "file:src/main/scala/de/htwg/se/maumau/util/textures/king_of_hearts.png"

      case Symbol.ASS if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/ace_of_spades.png"
      case Symbol.Two if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/2_of_spades.png"
      case Symbol.Three if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/3_of_spades.png"
      case Symbol.Four if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/4_of_spades.png"
      case Symbol.Five if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/5_of_spades.png"
      case Symbol.Six if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/6_of_spades.png"
      case Symbol.Seven if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/7_of_spades.png"
      case Symbol.Eight if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/8_of_spades.png"
      case Symbol.Nine if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/9_of_spades.png"
      case Symbol.Ten if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/10_of_spades.png"
      case Symbol.Jack if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/jack_of_spades.png"
      case Symbol.Lady if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/queen_of_spades.png"
      case Symbol.King if (color == Spades) => "file:src/main/scala/de/htwg/se/maumau/util/textures/king_of_spades.png"

      case Symbol.ASS if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/ace_of_clubs.png"
      case Symbol.Two if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/2_of_clubs.png"
      case Symbol.Three if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/3_of_clubs.png"
      case Symbol.Four if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/4_of_clubs.png"
      case Symbol.Five if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/5_of_clubs.png"
      case Symbol.Six if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/6_of_clubs.png"
      case Symbol.Seven if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/7_of_clubs.png"
      case Symbol.Eight if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/8_of_clubs.png"
      case Symbol.Nine if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/9_of_clubs.png"
      case Symbol.Ten if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/10_of_clubs.png"
      case Symbol.Jack if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/jack_of_clubs.png"
      case Symbol.Lady if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/queen_of_clubs.png"
      case Symbol.King if (color == Clubs) => "file:src/main/scala/de/htwg/se/maumau/util/textures/king_of_clubs.png"

      case Symbol.ASS if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/ace_of_diamonds.png"
      case Symbol.Two if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/2_of_diamonds.png"
      case Symbol.Three if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/3_of_diamonds.png"
      case Symbol.Four if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/4_of_diamonds.png"
      case Symbol.Five if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/5_of_diamonds.png"
      case Symbol.Six if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/6_of_diamonds.png"
      case Symbol.Seven if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/7_of_diamonds.png"
      case Symbol.Eight if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/8_of_diamonds.png"
      case Symbol.Nine if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/9_of_diamonds.png"
      case Symbol.Ten if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/10_of_diamonds.png"
      case Symbol.Jack if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/jack_of_diamonds.png"
      case Symbol.Lady if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/queen_of_diamonds.png"
      case Symbol.King if (color == Diamonds) => "file:src/main/scala/de/htwg/se/maumau/util/textures/king_of_diamonds.png"
    }
  }
}
