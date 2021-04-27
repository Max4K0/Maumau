package de.htwg.se.maumau.model

import Color.Color
import Symbol.Symbols
case class Card(color: Color, symbol: Symbols) {
  override def toString:String = {
    symbol match {
      case Symbol.ASS => color.toString.charAt(0) + " A"
      case Symbol.Two => color.toString.charAt(0) +" 2"
      case Symbol.Three => color.toString.charAt(0) +" 3"
      case Symbol.Four => color.toString.charAt(0) +" 4"
      case Symbol.Five => color.toString.charAt(0) +" 5"
      case Symbol.Six => color.toString.charAt(0) +" 6"
      case Symbol.Seven => color.toString.charAt(0) +" 7"
      case Symbol.Eight => color.toString.charAt(0) +" 8"
      case Symbol.Nine => color.toString.charAt(0) +" 9"
      case Symbol.Ten => color.toString.charAt(0) +" 10"
      case Symbol.Jack => color.toString.charAt(0) +" J"
      case Symbol.Lady => color.toString.charAt(0) +" L"
      case Symbol.King => color.toString.charAt(0) +" K"
    }
  }
}
