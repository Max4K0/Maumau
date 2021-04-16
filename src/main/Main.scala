package main

object Main {

  def main(args: Array[String]) {
    val arraystring = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val newarraystring = for(array <- arraystring if array < 5)yield array

    println("Hello World");
    println(arraystring)
    println(newarraystring)


  }

}




