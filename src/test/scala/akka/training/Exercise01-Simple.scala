/*
 * Copyright 2011 Typesafe Inc.
 *
 * Unless otherwise agreed, training materials may only be used for
 * educational and reference purposes by individual named participants
 * in a training course offered by Typesafe or a Typesafe training partner.
 * Unauthorized reproduction, redistribution, or use of this material is prohibited.
 */
package akka.training

/**
 * Exercise 1 - "Simple is beautiful"
 *
 * In this exercise we will learn how to create our first Akka Actor,
 * the "ToyActor", the purpose of this Actor to print some of the messages it gets
 *
 * We will learn how to create Actors, start them, receive message
 * On top of that we will learn how to write unit tests using ScalaTest!
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 *
 * Below is the blueprint of our soon to be ToyActor,
 * this is where you will implement the behavior of the actor.
 *
 * Underneath the ToyActor-class is the unit-test you will create
 * to verify your implementation.
 *
 * To verify that your code works you can run it in Eclipse
 * or executing the following command in sbt:
 * sbt > test-only akka.training.ToyActorSpec
 */
class ToyActor extends Actor {

    //TODO: Then we need to implement the receive-method which should print out:
    //      a message that you got a string when you receive a String
    //      a message that you got an integer when you receive an Int
    //      "something else" when it receives something else
  protected def receive = {
    case string:String => println("""received string "%s" """.format(string))
    case i:Int => println("""received integer "%d" """.format(i))
    case _ => println("Got something else")
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.JournerySpec
 */
@RunWith(classOf[JUnitRunner])
class ToyActorSpec extends AkkaTrainingTest {
  "Exercise1" should {
    "teach you how to create, start and stop an actor" in {
      val actorRef = actorOf[ToyActor]
      actorRef must not be null
      actorRef.start()
      actorRef.isRunning must be (true)
      actorRef.stop()
      actorRef.isRunning must not be(true)
    }

    "teach you how to send your very first message" in {
      val actorRef = actorOf[ToyActor]
     actorRef.start()
      actorRef ! "hej"
      actorRef ! 4711
      actorRef ! actorRef
      //actorRef ! PoisonPill
      val future = actorRef ? PoisonPill
      val f2 = future.await
      f2.exception must not be None //.exception must not be None
      Thread.sleep(1000)
      actorRef.isRunning must be (false)
    }
  }
}
