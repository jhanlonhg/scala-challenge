ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "scala-challenge",
    idePackagePrefix := Some("org.hanlonjohn23"),
    assembly / assemblyJarName := "q02.jar"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9"
libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.1"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"
