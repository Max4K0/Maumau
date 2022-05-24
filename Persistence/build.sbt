scalaVersion := "3.1.1"
name := "Maumau-Persistence"
version := "0.5.0-SNAPSHOT"


val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"

//Dependency Injection
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13)

libraryDependencies ++= Seq(
  ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)
)
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"

//FileIO
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"

//Database
libraryDependencies +=  "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"
libraryDependencies +=  ("com.typesafe.slick" %% "slick" % "3.4.0-M1").cross(CrossVersion.for3Use2_13)
libraryDependencies +=  ("com.typesafe.slick" %% "slick-hikaricp" % "3.4.0-M1").cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT")
libraryDependencies +=  "org.postgresql" % "postgresql" % "42.3.4"

resolvers += "jitpack" at "https://jitpack.io"

