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
 * Exercise 10 - "Super Vision"
 *
 * In this exercise we will learn about Supervisors by supervising 
 * a fault tolerant faulty echo actor.
 *
 * We will learn how to create Actors, start them, receive messages and reply with a response.
 * On top of that we will learn how to write unit tests using ScalaTest!
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import org.scalatest.junit.JUnitRunner
import akka.config.Supervision._
import akka.dispatch._
import akka.routing._
import java.util.concurrent.CountDownLatch
import java.io.IOException

import akka.testkit.TestActorRef
import org.junit.runner.RunWith

import akka.testkit.TestKit
import akka.util.duration._

/**
 *
 * Below you'll find the classes defining our supervisor that
 * makes manages the Faulty Echo Actor.
 *
 * To verify that your code works as expected run:
 * sbt > test-only akka.training.SupervisionActorSpec
 */
//States that our Echo Actor can be in 
object EchoStates extends Enumeration {
  val NeverStarted, Started, Restarted, Stopped = Value
}
object FailureException extends Exception("Expected")

//Our implementation of a faulty, but fault tolerant Echo Actor
class FaultyEchoActor extends Actor {
  import EchoStates._
  import SuperVisionSupervisor._

  var state = NeverStarted

  def receive = {
    case Failure => throw FailureException
    case m => { self.channel tryTell m }
  }

  //TODO: Override preStart, postRestart, postStop and 
  // 	  update the state var of the actor
}

//TODO: Complete this Fault Handler trait. Set the number of restarts to be low like 2 within 5 secs
trait DefaultFaultHandler { this: Actor =>
}

object SuperVisionSupervisor {
  case class Register(actor: ActorRef)
  case object Failure
}

//SuperVision enables you to see if your supervised actors are alive
class SuperVisionSupervisor(testKit : TestKit)  { //TODO: Create a default fault-handling Actor
  import SuperVisionSupervisor._
  
  def receive : Receive = {
    case Register(actor) => //TODO: link the actor 
    case m @ MaximumNumberOfRestartsWithinTimeRangeReached(victimActorRef, maxNrOfRetries, withinTimeRange, lastExceptionCausingRestart) =>
      //TODO: when this happens we want forward the message to the testKit.self
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.SupervisionSpec
 */
@RunWith(classOf[JUnitRunner])
class SupervisionSpec extends AkkaTrainingTest with TestKit {
  import SuperVisionSupervisor._

  "Exercise10" should {
    "teach you about the life-cycle hooks in Actors" in {
      //TODO: Create and start a *TestActorRef*

      //TODO: Check that the state Actor is correct by using underlying Actor 

      //TODO: Stop the Actor 

      //TODO: Check that the state is correct by using underlying Actor 
    }

    "teach you about Supervisors" in {
      //TODO: Create and start 1 FaultyEchoActors 

      //TODO: Create a Supervisor and register the actor 

      //TODO: Verify that you get the expected result from the actor

      //TODO: Send 1 Failure to the actor 

      //TODO: Verify that you still get the expected results from the echo actor

      //TODO: Check that the Supervisor behaves consistently:  
      //      Send more Failure messages than the limit specified in the fault tolerance strategy,
      //      then check if _this_ testkit class gets the *expected* msg MaximumNumberOfRestartsWithinTimeRangeReached 
      //      *within* a limit of 1sek. An example of what the expected message could look like:
      //      MaximumNumberOfRestartsWithinTimeRangeReached(actor, Some(2), Some(5000), FailureException)

      //TODO: Stop the supervisor
    }
  }
}
