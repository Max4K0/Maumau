package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

object Symbol extends Enumeration {
  type Symbols = Value
  val ASS, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
  Jack, Lady, King = Value

  def fromString(s : String) : Symbols =
    s match {
      case "A" => ASS
      case "2" => Two
      case "3"  => Three
      case "4"  => Four
      case "5"  => Five
      case "6" => Six
      case "7"  => Seven
      case "8"  => Eight
      case "9"  => Nine
      case "10" => Ten
      case "J"  => Jack
      case "L" => Lady
      case "K" => King
    }
}
