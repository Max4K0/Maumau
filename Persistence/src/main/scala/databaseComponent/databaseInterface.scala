package databaseComponent

trait databaseInterface {
  def writePlayer(name: String): String
  def readPlayer(): String
  def writeCard(card: String): String
  def readCard(): String
}
