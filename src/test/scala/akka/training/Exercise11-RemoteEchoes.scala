/*
 * Copyright 2011 Typesafe Inc.
 * 
 * Unless otherwise agreed, training materials may only be used for
 * educational and reference purposes by individual named participants
 * in a training course offered by Typesafe or a Typesafe training partner.
 * Unauthorized reproduction, redistribution, or use of this material is prohibited.
 */
          
/**
 * Exercise 11 - "Remote echoes"
 *
 * In this exercise we will learn how to expose our actors as
 * server managed remote actors.
 *
 * Let's go!
 */

/*
*  Open 2 consoles
*  In the first, navigate to this project
*
*  Then type:
*  sbt<ENTER>
*
*  Then type:
*  console<ENTER>
*
*  then type:
*  scala> import akka.actor._
*  scala> import akka.actor.Actor._
*
    *  Then start up the remoting by typing:
    *  scala> remote.start("localhost", 1337)
    *
    *  Then register an EchoActor as a server-managed remote actor by typing:
    *  scala> remote.register("echoservice", actorOf[akka.training.EchoActor].start)
    *
*
*
*  Then go to your second console
*  Navigate to this project
*
*  Then type:
*  sbt<ENTER>
*
*  Then type:
*  console<ENTER>
*
*  then type:
*  scala> import akka.actor._
*  scala> import akka.actor.Actor._
*
*  Then start up the remoting by typing:
*  scala>remote.start("localhost", 12345)
*
*  Then fetch the echoservice:
*  val echoActor = remote.actorFor("echoservice", "localhost", 1337)
*
*  Then send messages to the echo actor :-)
*
*  Have fun!
*/