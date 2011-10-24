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
 * Exercise 4 - "Akkumulator"
 *
 * In this exercise we will learn how to manage state within an Actor,
 * how to define our own message types and how to handle different messages
 * within the same Actor.
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestActorRef

/**
 * Below is the blueprint of our soon to be AccumulatorActor,
 * this is where you will implement the behavior of the actor.
 *
 * Underneath the AccumulatorActor-class is the unit-test you will create
 * to verify your implementation.
 *
 * To verify that your code works as expected run:
 * sbt > test-only akka.training.AccumulatorActorSpec
 */

//First we create a companion object for our AccumulatorActor type,
//it's purpose will be to act as a namespace for the message types we
//will define.

object AccumulatorActor {
  //TODO: Create a message type that will be used to add a supplied integer number to the internal number of the Actor
  //TODO: Create a message type that will be used to query the internal number of the Actor
  //TODO: Create a message type that will be used to set a supplied integer number as the current internal number of the Actor
}

class AccumulatorActor { //TODO: We need to make sure this is an Actor
  import AccumulatorActor._
  //TODO: Here we will define field(s) that will hold our state

  //TODO: Then we need to implement the receive-method that:
  //      Given the set-message set the supplied number as the internal number
  //      Given the add-message adds the supplied number to the internal number
  //      Given the sum-message responds with the internal number
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.AccumulatorActorSpec
 */
@RunWith(classOf[JUnitRunner])
class AccumulatorActorSpec extends AkkaTrainingTest {
  import AccumulatorActor._ //This imports our message types into the scope of the tests

  "Exercise4" should {
    "teach you how to handle multiple types of messages in an actor" in {
      //TODO: Create and start a *TestActorRef* of AccumulatorActor

      //TODO: Verify, without sending a message to the actor, that it can accept all different message types
      //      defined in the AccumulatorActor object

      //TODO: Stop the AccumulatorActor
    }

    "teach you how to manage state inside an actor" in {
      //TODO: Create and start an instance of the AccumulatorActor

      //TODO: Send the set-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Send the add-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Send the set-message to the ActorRef and use the sum-message to verify that you get the expected result

      //TODO: Stop the AccumulatorActor
    }
  }
}
