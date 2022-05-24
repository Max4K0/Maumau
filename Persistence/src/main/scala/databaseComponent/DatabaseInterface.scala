package databaseComponent

trait DatabaseInterface {
  def writeCard(card: String): Int
  def readCard(id: Int): String
  def writeCardList(cards: List[String]): List[Int]
  def readCardList(id: Int): List[String]
  def writeDeck(deck: List[String]): List[Int]
  def readDeck(id: Int): List[String]
  def writeDeckList(decks: List[List[String]]): List[Int]
  def readDeckList(id: Int): List[List[String]]
  def writePlayer(player: String): Int
  def readPlayer(id: Int): String
  def writePlayerList(players: List[String]): List[Int]
  def readPlayerList(id: Int): List[String]
  def writeTable(table: String): Unit
  def readTable(): String
}
