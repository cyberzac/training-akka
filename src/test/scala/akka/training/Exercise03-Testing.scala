/*
Exercise04-Testing.scala * Copyright 2011 Typesafe Inc.
 *
 * Unless otherwise agreed, training materials may only be used for
 * educational and reference purposes by individual named participants
 * in a training course offered by Typesafe or a Typesafe training partner.
 * Unauthorized reproduction, redistribution, or use of this material is prohibited.
 */
package akka.training

/**
 * Exercise 3  - "Testing Akka"
 *
 * In this exercise we will learn how to test actors. We will be using:
 *  - TestActorRef for unit testing; and
 *  - the TestKit trait for integration testing
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestActorRef
import javax.swing.text.DefaultEditorKit.CopyAction

//This is our very own Special Echo Actor
//It is special because it fails when you sent him an exception
class SpecialEchoActor extends Actor {

  def receive = {
    case m: String => self.channel tryTell echo(m)
    case e: Exception => throw new Exception(e.getMessage())
    case (a: ActorRef, m: Any) => a ! m
  }

  def echo(m: String): String = {
    m
  }
}

class CollActor extends Actor {
  var messages = List[String]()
  protected def receive = {
    case s:String => messages = s :: messages
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by typing this in sbt:
 *
 * test-only akka.training.TestActorSpec
 */
@RunWith(classOf[JUnitRunner])
class TestActorSpec extends AkkaTrainingTest {
  //TODO: mixin the TestKit

  "Exercise3" should {
    "teach you to how you can unit test your Actors" in {
      val sar = TestActorRef[SpecialEchoActor]
      sar.start()

      (sar ? "nisse").get must be("nisse")


      intercept[Exception] { (sar ? new Exception("Boom!")).get}.getMessage must be ("Boom!")

      val car = TestActorRef[CollActor]
      car.start()

      (sar ! (car, "loop"))

      val collActor = car.underlyingActor
      (collActor.messages(0)) must be ("loop")

          // { (sar ? new Exception("Boom!")).get } must produce[Exception]
       evaluating { (sar ? 4712).get } must produce[UnhandledMessageException]



      //TODO: Send an Exception to the TestActorRef and see what happens when using the CallingThreadDispatcher in the TestActorRef
      sar ! new Exception("Look! A stacktrace!")
    }

    "teach you to write integration tests using TestKit" in {
      //TODO: Create and start a *ActorRef*

      //TODO: Send one message and make sure it is the ONLY one that is received within 200 millis

      //TODO: Send a message a and make sure that the returned message type is really String

      //TODO: Send N messages and make sure that the received results is what you expected

      //TODO: Send an Exception to the _ActorRef_ and see what happens
      //echo ! new Exception("You will not see me")

    }

    "teach you use TestProbes in your tests" in {
      //TODO: Create and start a *ActorRef* of SpecialEchoActor

      //TODO: Create a TestProbe

      //TODO: Use the probe to verify that you are sending the correct message
    }
  }
}
