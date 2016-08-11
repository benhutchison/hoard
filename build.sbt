lazy val root = project.in(file(".")).aggregate(hoardJS, hoardJVM).
  settings(
    publishArtifact := false,
    crossScalaVersions := Seq("2.11.8"),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    sonatypeProfileName := "com.github.benhutchison",
    {
      import ReleaseTransformations._
      releaseProcess := Seq[ReleaseStep](
        checkSnapshotDependencies,
        inquireVersions,
        runClean,
        runTest,
        setReleaseVersion,
        commitReleaseVersion,
        tagRelease,
        ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
        setNextVersion,
        commitNextVersion
    )}
  )

lazy val hoardCross = crossProject.in(file(".")).
  settings(
    name := "hoard",
    organization := "com.github.benhutchison",
    version := "0.1",
    scalaVersion := "2.11.8",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")),
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats" % "0.7.0-SNAPSHOT",
      "org.scalatest" %%% "scalatest" % "3.0.0-M15" %  "test"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.8.0",
    publishTo <<= version { (v: String) =>
      Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
    },
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