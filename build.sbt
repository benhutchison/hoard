lazy val root = project.in(file(".")).aggregate(hoardJS, hoardJVM).
  settings(
    skip in publish := true,
    crossScalaVersions := Seq("2.11.8", "2.12.4"),
    sonatypeProfileName := "com.github.benhutchison"
  )

lazy val hoardCross = crossProject.in(file(".")).
  settings(
    name := "hoard",
    organization := "com.github.benhutchison",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "1.0.0-RC1",
      "com.github.mpilquist" %% "simulacrum" % "0.11.0",
      "org.typelevel" %%% "cats-laws" % "1.0.0-RC1" % "test",
      "org.scalatest" %%% "scalatest" % "3.0.0" %  "test",
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    publishMavenStyle := true,
    homepage := Some(url("https://github.com/typelevel/hoard")),
    developers := List(Developer("benhutchison", "Ben Hutchison", "brhutchison@gmail.com", url = url("https://github.com/benhutchison"))),
    scmInfo := Some(ScmInfo(url("https://github.com/benhutchison/hoard"), "scm:git:git@github.com:benhutchison/hoard.git")),
    licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0")),
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
  )

lazy val hoardJVM = hoardCross.jvm
lazy val hoardJS = hoardCross.js