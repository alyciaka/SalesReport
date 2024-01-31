  val ScalaTestVersion = "3.2.9"

  lazy val root = (project in file("."))
    .configs(IntegrationTest)
    .settings(
      name := "BelieveTest",
      inConfig(IntegrationTest)(Defaults.testSettings),
      inThisBuild(Seq(
        scalaVersion := "2.12.11"
      )),
      libraryDependencies ++= Seq(
        "org.apache.spark" %% "spark-core" % "3.1.0",
        "org.apache.spark" %% "spark-sql" % "3.1.0",
        "io.delta" %% "delta-core" % "1.0.0",
        "org.scalatest" %% "scalatest" % ScalaTestVersion % "it,test"
      )
    )
