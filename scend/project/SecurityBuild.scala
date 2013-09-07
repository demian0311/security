import sbt._
import sbt.Keys._

object SecurityBuild extends Build {

  lazy val security = Project(
    id = "security",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "security",
      organization := "com.neidetcher.security",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.2"
      // add other settings here
    )
  )
}
