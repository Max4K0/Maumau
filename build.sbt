name          := "main"
organization  := ""
version       := "0.0.1"
scalaVersion  := "2.13.5"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"
libraryDependencies += "org.openjfx" % "javafx" % "12.0.2" pomOnly()
libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"


lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}
coverageExcludedPackages := "de.htwg.se.maumau.aview.GUIApp"
coverageExcludedPackages := "de.htwg.se.maumau.aview.GUI"
// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>  "org.openjfx" % s"javafx-$m" % "11" classifier osName)
