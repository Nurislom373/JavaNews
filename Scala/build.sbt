import scala.collection.immutable

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.2"

lazy val root = (project in file("."))
  .settings(
    name := "Scala",
    idePackagePrefix := Some("uz.khasanof")
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test, // ScalaTest for unit testing
  "org.junit.jupiter" % "junit-jupiter-api" % "5.10.0" % Test, // JUnit 5 API
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.0" % Test // JUnit 5 Engine
)