lazy val root = project.in(file(".")).aggregate(hoardJS, hoardJVM).
  settings(
    publishArtifact := false,
    crossScalaVersions := Seq("2.11.8", "2.12.0"),
    sonatypeProfileName := "com.github.benhutchison"
  )

lazy val hoardCross = crossProject.in(file(".")).
  settings(
    name := "hoard",
    organization := "com.github.benhutchison",
    scalaVersion := "2.12.0",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "0.8.1",
      "org.typelevel" %%% "cats-laws" % "0.8.1" % "test",
      "org.scalatest" %%% "scalatest" % "3.0.0" %  "test"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.10.0",
    publishMavenStyle := true,
    pomExtra :=
      <url>https://github.com/benhutchison/hoard</url>
      <scm>
        <url>git://github.com/benhutchison/hoard.git</url>
      </scm>
      <developers>
        <developer>
          <id>benhutchison</id>
          <name>benhutchison</name>
          <url>https://github.com/benhutchison</url>
        </developer>
      </developers>,
    licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0"))
  )

lazy val hoardJVM = hoardCross.jvm
lazy val hoardJS = hoardCross.js