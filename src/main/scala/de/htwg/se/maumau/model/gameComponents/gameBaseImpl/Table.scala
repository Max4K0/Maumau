package de.htwg.se.maumau.model.gameComponents.gameBaseImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.maumau.util.{State, nextPlayerEvent, winEvent}
import io.circe.generic.JsonCodec
import scala.util.Try

import scala.util.Random
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import io.circe.generic.semiauto.*
import io.circe.*
import io.circe.Decoder.Result

case class Table(player: List[Player] = List[Player](Player("P1", Deck()), Player("P2", Deck())), tableDecks: List[Deck] = List[Deck](Deck().fillDeck.shuffleDeck(new Random(1)), Deck(List[Card]()))) extends AbstractTable(player: List[Player], tableDecks: List[Deck]) {


  implicit val cardDecoder : Decoder[Card] = Decoder
    .decodeString
    .emapTry(str => Try(Card(Color.fromString(str.split(" +")(0)), Symbol.fromString(str.split(" +")(1)))))
  implicit val cardEncoder : Encoder[Card] = (a: Card) => Json.fromString(a.toString)

  implicit val cardListDecoder : Decoder[List[Card]] = Decoder.decodeList[Card](cardDecoder)
  implicit val cardListEncoder : Encoder[List[Card]] = Encoder.encodeList[Card](cardEncoder)


  implicit val deckDecoder : Decoder[Deck] = Decoder
    .decodeList[Card]
    .emapTry(c => Try(Deck().useDeck(c)))
  implicit val deckEncoder : Encoder[Deck] = (d: Deck) => cardListEncoder(d.cards)


  implicit val deckListDecoder : Decoder[List[Deck]] = Decoder.decodeList[Deck](deckDecoder)
  implicit val deckListEncoder : Encoder[List[Deck]] = Encoder.encodeList[Deck](deckEncoder)

  implicit val playerDecoder : Decoder[Player] = Decoder
    .decodeJsonObject
    .emapTry(js =>
      Try(
        Player(
          js("name").toString,
          decodePlayerDeck(deckDecoder.decodeJson(js("playerDeck").get))
        )
      )
    )
  implicit val playerEncoder : Encoder[Player] = (player : Player) => Json.obj(
    "name" -> Json.fromString(player.toString),
    "playerDeck" -> deckEncoder(player.playerDeck)
  )
  def decodePlayerDeck(res : Result[Deck]): Deck = res match {
    case Right(result) => result
    case Left(result) => throw Exception(s"Failed parsing Deck JSON\n$result")
  }

  implicit val playerListDecoder : Decoder[List[Player]] = Decoder.decodeList[Player](playerDecoder)
  implicit val playerListEncoder : Encoder[List[Player]] = Encoder.encodeList[Player](playerEncoder)

  implicit val tableDecoder : Decoder[Table] = Decoder
    .decodeJsonObject
    .emapTry(js =>
      Try(
        Table(
          decodeTablePlayerList(playerListDecoder.decodeJson(js("player").get)),
          decodeTableDeck(deckListDecoder.decodeJson(js("tableDecks").get))
        )
      )
    )
  implicit val tableEncoder : Encoder[Table] = (table : Table) => Json.obj(
    "player" -> playerListEncoder(table.player),
    "tableDecks" -> deckListEncoder(table.tableDecks)
  )
  def decodeTableDeck(res : Result[List[Deck]]) : List[Deck] = res match {
    case Right(result) => result
    case Left(result) => throw Exception(s"Failed parsing List[Deck] JSON\n$result")
  }
  def decodeTablePlayerList(res : Result[List[Player]]) : List[Player] = res match {
    case Right(result) => result
    case Left(result) => throw Exception(s"Failed parsing List[Player] JSON\n$result")
  }



  override def toJson: String = this.asJson.noSpaces

  override def fromJson(json: String): Table = {
    decode[Table](json) match {
      case Right(res) => res
      case Left(res) => throw Exception(s"failed parsing JSON\n$res")
    }
  }

  override def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean = {
    val currentPlayer = table.player(playerNumber)
    ((currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == tableDecks(1).cards.last.symbol) ||
      (currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.color == tableDecks(1).cards.last.color) ||
      currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == Symbol.Jack)
  }

  override def checkDeck(table: Table, playerNumber: Int): Boolean = {
    val currentPlayer = table.player(playerNumber)
    currentPlayer.playerDeck.cards.map(Card => Card.symbol).contains(tableDecks(1).cards.last.symbol) ||
      currentPlayer.playerDeck.cards.map(Card => Card.color).contains(tableDecks(1).cards.last.color)
  }

  override def addPlayers(table: Table, name: String, playerNumber: Int): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwCards(5, emptyDeck)
    val newPlayer = Player(name, changedDeck._1)
    val newDeck = changedDeck._2
    val newTable = table.copy(player = table.player.updated(playerNumber, newPlayer), tableDecks.updated(0, newDeck))
    newTable
  }

  override def throwFirstCard(table: Table): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwOneCard(1, emptyDeck)
    val newTable = table.copy(player = player, tableDecks.updated(1, changedDeck._1).updated(0, changedDeck._2))
    newTable
  }

  override def throwCard(table: Table, playerNumber: Int, cardNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = currentPlayer.playerDeck.throwOneCard(cardNumber, tableDecks(1))
    val newPlayer = Player(currentPlayer.name, changedDeck._2)
    val newTable = table.copy(player.updated(playerNumber, newPlayer), tableDecks.updated(1, changedDeck._1))
    val result = if (newTable.player(playerNumber).playerDeck.equals(Deck())) {
      State.handle(winEvent())
      println(State.state)
      //System.exit(1)
      table
    } else State.handle(nextPlayerEvent())
    newTable
  }

  override def takeCard(table: Table, playerNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = tableDecks.head.throwCards(1, currentPlayer.playerDeck)
    val newPlayer = Player(currentPlayer.name, changedDeck._1)
    val newTable = table.copy(player.updated(playerNumber, newPlayer), tableDecks.updated(0, changedDeck._2))
    newTable
  }


  override def toString: String = {
    val playerNumber = if (State.state == "Player1:") 1 else 0
    val table = new StringBuilder("\u001B[48;5;15m" + " tablecards: ")
    table.append(tableDecks(1).cards.last.UTFSymbols)
    val hand = new StringBuilder(" Player" + (playerNumber + 1) + ": ")

    hand.append(player(playerNumber).playerDeck.cards.map(Card => Card.UTFSymbols).mkString(" "))

    //hand.append(player(playerNumber).playerDeck.cards.map(Card => Card.Path).mkString(" "))
    val Statement = new StringBuilder()
    Statement.append(table).append("\n\n")
    Statement.append(hand)
    Statement.toString()
  }

}
