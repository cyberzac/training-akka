scalaVersion := "2.9.1"

name := "Akka Training"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "se.scalablesolutions.akka" % "akka-actor" % "1.2"

libraryDependencies += "se.scalablesolutions.akka" % "akka-remote" % "1.2"

libraryDependencies += "se.scalablesolutions.akka" % "akka-stm" % "1.2"

libraryDependencies += "se.scalablesolutions.akka" % "akka-testkit" % "1.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

libraryDependencies += "junit" % "junit" % "4.5" % "test"

scalacOptions += "-deprecation" //We use this to make sure we are not using any deprecated features
