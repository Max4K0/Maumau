package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

object Symbol extends Enumeration {
  type Symbols = Value
  val ASS, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
  Jack, Lady, King = Value

  def fromString(s : String) : Symbols =
    s match {
      case "ASS" => ASS
      case "Two" => Two
      case "Three"  => Three
      case "Four"  => Four
      case "Five"  => Five
      case "Six" => Six
      case "Seven"  => Seven
      case "Eight"  => Eight
      case "Nine"  => Nine
      case "Ten" => Ten
      case "Jack"  => Jack
      case "Lady" => Lady
      case "King" => King
    }
}
