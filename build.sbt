val scala3Version = "3.1.1"

scalacOptions ++= Seq(
  "--source:3.0-migration",
  "-explain",
  "-explain-types"
)
lazy val controller = (project in file("controller"))
lazy val util = (project in file("util"))
lazy val model = (project in file("model"))
lazy val aview = (project in file("aview"))

lazy val root = project
  .in(file("."))
  .aggregate(controller, util, model, aview)
  .settings(
    name := "Maumau",

    scalaVersion := scala3Version,

    //Testing
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",

    //GUI- Swing/FX
    libraryDependencies += "org.scalafx" %% "scalafx" % "17.0.1-R26",

    //Dependency Injection
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.1").cross(CrossVersion.for3Use2_13),

    libraryDependencies += ("com.typesafe.akka" %% "akka-actor-typed" % "2.6.19").cross(CrossVersion.for3Use2_13),

    //FileIO
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5",
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN"),
    jacocoExcludes in Test := Seq(
      "de.htwg.se.maumau.aview.*",
      "de.htwg.se.maumau.model.gameComponents.fileIOComponent.*"
    ),

    libraryDependencies ++= {
      lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux") => "linux"
        case n if n.startsWith("Mac") => "mac"
        case n if n.startsWith("Windows") => "win"
        case _ => throw new Exception("Unknown platform!")
      }
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map( m=>  "org.openjfx" % s"javafx-$m" % "17.0.1" classifier osName)
    },

    //Excluding Folders for Coveralls
    //coverageExcludedPackages := "de.htwg.se.maumau.aview.GUIApp; de.htwg.se.maumau.aview.GUI; de.htwg.se.maumau.model.gameComponents.fileIOComponent.*;"
)
.enablePlugins(JacocoCoverallsPlugin)