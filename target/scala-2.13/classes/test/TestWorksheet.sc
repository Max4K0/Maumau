import org.scalatest._
import org.scalatest.matchers.should.Matchers._

val arraystring = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
val newarraystring = for(array <- arraystring if array < 5)yield array


val test = "Hello World"
println("Hello World")
println(arraystring)
println(newarraystring)

test should be("Hello World")
