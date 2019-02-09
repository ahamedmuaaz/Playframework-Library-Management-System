name := """LibraryManagmentSystem"""
organization := "WestminsterLibrary"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += javaJdbc

libraryDependencies ++= Seq(
  "org.mongodb" % "mongo-java-driver" % "3.6.3"
)



libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.2.4"
)