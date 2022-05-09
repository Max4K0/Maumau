scalaVersion := "3.1.1"
name := "Maumau-Persistence"
version := "0.5.0-SNAPSHOT"


val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"


val circeVersion = "0.14.1"

scalacOptions ++= Seq(
  "--source:3.0-migration",
  "-explain",
  "-explain-types"
)


//Testing
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

//GUI- Swing/FX
libraryDependencies += "org.scalafx" %% "scalafx" % "17.0.1-R26"

//Dependency Injection
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13)

libraryDependencies ++= Seq(
  ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)
)
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"

libraryDependencies ++= Seq(
  ("io.circe" %% "circe-core"% circeVersion).cross(CrossVersion.for3Use2_13),
  ("io.circe" %% "circe-generic"% circeVersion).cross(CrossVersion.for3Use2_13),
  ("io.circe" %% "circe-parser"% circeVersion).cross(CrossVersion.for3Use2_13)
)

//FileIO
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"


