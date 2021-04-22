package de.htwg.se.maumau.model

import Color.Color
import Symbol.Symbols
case class Card(color: Color, symbol: Symbols) {

  override def toString:String = {
    symbol match {
      case Symbol.ASS => color.toString + " ASS"
      case Symbol.One => color.toString +" 1"
      case Symbol.Two => color.toString +" 2"
      case Symbol.Three => color.toString +" 3"
      case Symbol.Four => color.toString +" 4"
      case Symbol.Five => color.toString +" 5"
      case Symbol.Six => color.toString +" 6"
      case Symbol.Seven => color.toString +" 7"
      case Symbol.Eight => color.toString +" 8"
      case Symbol.Nine => color.toString +" 9"
      case Symbol.Ten => color.toString +" 10"
      case Symbol.Jack => color.toString +" J"
      case Symbol.Lady => color.toString +" L"
      case Symbol.King => color.toString +" K"
    }
  }
}
