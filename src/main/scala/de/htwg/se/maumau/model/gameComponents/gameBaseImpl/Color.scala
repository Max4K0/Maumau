package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

object Color extends Enumeration {
  type Color = Value
  val Clubs, Diamonds, Hearts, Spades = Value

  def fromString(s: String) : Color =
    s match {
      case "C" => Clubs
      case "D" => Diamonds
      case "H" => Hearts
      case "S" => Spades
    }
}
