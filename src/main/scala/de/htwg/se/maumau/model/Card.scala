package de.htwg.se.maumau.model

import java.awt.Color

case class Card(symbol: Symbol, color: Color) {
  object Symbol extends Enumeration {
    type Symbols = Symbol
    val ASS, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
    Jack, Lady, King = symbol
  }
  object Color extends Enumeration {
    type Colors = Symbol
    val Cross, Diamonds, Heart, Spades = Symbol
  }
  override def toString:String = {
    symbol match {
      case Symbol.ASS => color.toString.charAt(0) + " ASS"
      case Symbol.One => color.toString.charAt(0) + " 1"
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
    Symbol match {
      case Color.Cross => color.toString.charAt(0) + " Cross"
      case Color.Diamonds => color.toString.charAt(0) + " Diamonds"
      case Color.Heart => color.toString.charAt(0) + " Heart"
      case Color.Spades => color.toString.charAt(0) + " Spades"
    }
  }
  
}
