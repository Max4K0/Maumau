val scala3Version = "3.1.1"

val AkkaVersion = "2.6.19"
val AkkaHttpVersion = "10.2.9"


val circeVersion = "0.14.1"

javacOptions := Seq("javafx.platform=x11")

scalacOptions ++= Seq(
  "-explain",
  "-explain-types"
)

lazy val options = Seq(
  scalaVersion := scala3Version,


  resolvers += "jitpack" at "https://jitpack.io",

  //Testing
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",

  //GUI- Swing/FX
  libraryDependencies += "org.scalafx" %% "scalafx" % "17.0.1-R26",

  //Dependency Injection
  libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
  libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13),

  libraryDependencies ++= Seq(
    ("com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion).cross(CrossVersion.for3Use2_13),
    ("com.typesafe.akka" %% "akka-stream" % AkkaVersion).cross(CrossVersion.for3Use2_13),
    ("com.typesafe.akka" %% "akka-http" % AkkaHttpVersion).cross(CrossVersion.for3Use2_13)
  ),
  libraryDependencies +=  "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7",

  libraryDependencies ++= Seq(
    ("io.circe" %% "circe-core"% circeVersion).cross(CrossVersion.for3Use2_13),
    ("io.circe" %% "circe-generic"% circeVersion).cross(CrossVersion.for3Use2_13),
    ("io.circe" %% "circe-parser"% circeVersion).cross(CrossVersion.for3Use2_13)
  ),

  //Database
  libraryDependencies +=  "org.slf4j" % "slf4j-nop" % "2.0.0-alpha7",
  libraryDependencies +=  ("com.typesafe.slick" %% "slick" % "3.4.0-M1").cross(CrossVersion.for3Use2_13),
  libraryDependencies +=  ("com.typesafe.slick" %% "slick-hikaricp" % "3.4.0-M1").cross(CrossVersion.for3Use2_13),
  libraryDependencies += ("com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT"),
  libraryDependencies +=  "org.postgresql" % "postgresql" % "42.3.4",

  //FileIO
  libraryDependencies += ("com.github.slick.slick" % "slick_3" % "nafg~dottyquery-SNAPSHOT") ,
  libraryDependencies +=  "org.postgresql" % "postgresql" % "42.3.4",
  libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
  libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5",
  jacocoCoverallsServiceName := "github-actions",
  jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
  jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
  jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN"),
  jacocoExcludes in Test := Seq(
    "maumau.aview.*",
    "maumau.model.gameComponents.fileIOComponent.*"
  ),

  libraryDependencies ++= {
    lazy val osName = System.getProperty("os.name") match {
      case n if n.startsWith("Linux") => "linux-aarch64"
      case n if n.startsWith("Mac") => "mac-aarch64"
      case n if n.startsWith("Windows") => "win"
      case _ => throw new Exception("Unknown platform!")
    }
    Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
      .map( m=>  "org.openjfx" % s"javafx-$m" % "17.0.1" classifier osName)
  }

)

lazy val persistence = (project in file("Persistence"))
  .settings(
    name := "Maumau-Persistence",
    options
  )

lazy val root = project
  .in(file("."))
  .settings(
    name := "Maumau",
    version := "1.6",
    options
  ).enablePlugins(JacocoCoverallsPlugin)