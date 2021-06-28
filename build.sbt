name          := "main"
organization  := ""
version       := "0.0.1"
scalaVersion  := "2.13.5"

//Testing
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"

//GUI- Swing/FX
libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R24"
libraryDependencies += "org.openjfx" % "javafx" % "12.0.2" pomOnly()

//Dependency Injection
libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"

//FileIO
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC2"

lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

//Excluding Folders for Coveralls
coverageExcludedPackages := "de.htwg.se.maumau.aview.GUIApp; de.htwg.se.maumau.aview.GUI; de.htwg.se.maumau.model.gameComponents.fileIOComponent.*;"


// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>  "org.openjfx" % s"javafx-$m" % "16" classifier osName)
