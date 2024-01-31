val ScalaTestVersion = "3.2.9"
  /*
lazy val root = (project in file("."))
  .settings(
    name := "BelieveTest",
    Global / cancelable := true,
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    updateOptions := updateOptions.value.withLatestSnapshots(false),
    resolvers ++= Seq(
      "Maven Central" at "https://repo1.maven.org/maven2",
      Resolver.url("Artima Maven Repository", url("http://repo.artima.com/releases"))(Resolver.ivyStylePatterns)
    ),
    inThisBuild(Seq(
      scalaVersion := "2.12.11",
      IntegrationTest / fork := true,
      IntegrationTest / testForkedParallel := true,
      IntegrationTest / parallelExecution  := false,
      Test / fork := true,
      Test / testForkedParallel := true,
      Test / parallelExecution := true,
      concurrentRestrictions := Seq(Tags.limitAll(2)),
      testOptions in IntegrationTest += Tests.Argument("-oFD"),
      testOptions in Test += Tests.Argument("-oFD")
    )),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.1.0",
      "org.apache.spark" %% "spark-sql" % "3.1.0" ,
      "io.delta" %% "delta-core" % "1.0.0",
      "org.scalatest" %% "scalatest" % ScalaTestVersion % "it,test" // Spécifier "it,test" ici
    )
  )

   */

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
