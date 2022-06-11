scalaVersion := "3.1.1"
name := "Maumau-Persistence"
version := "1.6"


val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"

scalacOptions ++= Seq(
  "-Xignore-scala2-macros",
  "-explain",
  "-explain-types"
)
val circeVersion = "0.14.1"


resolvers += "jitpack" at "https://jitpack.io"

//Dependency Injection
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13)

libraryDependencies ++= Seq(
  ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
  ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)
)

//FileIO
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5"

libraryDependencies ++= Seq(
  ("io.circe" %% "circe-core"% circeVersion).cross(CrossVersion.for3Use2_13),
  ("io.circe" %% "circe-generic"% circeVersion).cross(CrossVersion.for3Use2_13),
  ("io.circe" %% "circe-parser"% circeVersion).cross(CrossVersion.for3Use2_13)
)

//Database
libraryDependencies +=  "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7"
//libraryDependencies +=  ("com.typesafe.slick" %% "slick" % "3.3.3").cross(CrossVersion.for3Use2_13)
libraryDependencies +=  ("com.typesafe.slick" %% "slick-hikaricp" % "3.3.3").cross(CrossVersion.for3Use2_13)
libraryDependencies += ("com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT")
libraryDependencies +=  "org.postgresql" % "postgresql" % "42.3.4"


