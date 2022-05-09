package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

object Color extends Enumeration {
  type Color = Value
  val Clubs, Diamonds, Hearts, Spades = Value

  def fromString(s: String) : Color =
    s match {
      case "Clubs" => Clubs
      case "Diamonds" => Diamonds
      case "Heart" => Hearts
      case "Spades" => Spades
    }
}
