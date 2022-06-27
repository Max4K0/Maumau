package databaseComponent

import io.circe.Json

trait DatabaseInterface {
  def createDB(): Unit
  def writeCardList(gameId: Int, deckId: Int, cards: List[String]): List[Int]
  def readCardList(deckId: Int, gameId: Int): Json
  def writeDeck(gameId: Int, deck: List[String]): Int
  def writeDeckList(gameId: Int, decks: List[String]): List[Int]
  def readDeckList(gameId: Int): Json
  def writePlayer(gameId: Int, name: String, deck: List[String]): String
  def readPlayer(gameId: Int, name: String): Json
  def writePlayerList(gameId: Int, players: List[String]): List[String]
  def readPlayerList(gameId: Int): Json
  def writeTable(table: String): Unit
  def readTable(): String
}
