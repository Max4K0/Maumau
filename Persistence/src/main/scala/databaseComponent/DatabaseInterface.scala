package databaseComponent

trait DatabaseInterface {
  def createDB(): Unit
  def writeCardList(deckId: Int, cards: List[String]): List[Int]
  def readCardList(id: Int): List[String]
  def writeDeck(deck: List[String]): Int
  def readDeck(id: Int): List[String]
  def writeDeckList(decks: List[String]): List[Int]
  def readDeckList(id: Int): List[List[String]]
  def writePlayer(name: String, deck: String): String
  def readPlayer(name: String): String
  def writePlayerList(players: List[String]): List[String]
  def readPlayerList(name: String): List[String]
  def writeTable(table: String): Unit
  def readTable(): String
  def printDB(): Unit
}
