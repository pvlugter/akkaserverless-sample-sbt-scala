lazy val sample = project
  .in(file("."))
  .settings(
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "com.akkaserverless" % "akkaserverless-java-sdk" % "0.7.0-beta.18" % "compile;protobuf",
      // JSON-based logging via logback
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "ch.qos.logback.contrib" % "logback-json-classic" % "0.1.5",
      "ch.qos.logback.contrib" % "logback-jackson" % "0.1.5"
    ),
    Compile / PB.targets += scalapb.gen(grpc = false) -> (Compile / sourceManaged).value,
    run / fork := true
  )
