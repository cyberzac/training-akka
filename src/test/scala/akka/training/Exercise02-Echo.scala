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
 * Exercise 1 - "Echoes in the night"
 *
 * In this exercise we will learn how to create a more useful Akka Actor,
 * the "EchoActor", the purpose of this Actor is to echo back incoming messages
 * to whomever sent the message. If there is no way to reply, it should do nothing.
 *
 * We will learn how to receive messages in Actors and reply with a response.
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import com.sun.org.apache.xpath.internal.axes.ReverseAxesWalker
import actors.remote.RemoteActor

class EchoActor extends Actor {
  def receive = {
    case x => self reply x
  }
}

class ReverseActor(doublerActor:ActorRef) extends Actor {
  protected def receive = {
    case s:String => doublerActor forward s.reverse
  }
}

class DoublerActor() extends Actor {
  protected def receive = {
    case s:String => self reply "%s %s".format(s, s)
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.EchoActorSpec
 */
@RunWith(classOf[JUnitRunner])
class EchoActorSpec extends AkkaTrainingTest {
  "Exercise2" should {
    "teach you how to reply to a message" in {
      val echoActorRef = actorOf[EchoActor]
      echoActorRef.start()
      val response = (echoActorRef ? "ping").as[String]
      response.get must be("ping")
      echoActorRef.stop()
    }

    "teach you what happens when you send a message to a stopped actor" in {
      val echoActorRef = actorOf[EchoActor]
      echoActorRef.start()
      echoActorRef.stop()
      evaluating {
        echoActorRef ? "ping"
      } must produce[ActorInitializationException]
    }

    "teach you how to echo back messages consistently" in {
      val echoActorRef = actorOf[EchoActor]
      echoActorRef.start()
      for (msg <- List("ping", "pong", "pang", "pung")) {
        (echoActorRef ? msg).as[String].get must be(msg)
      }
      echoActorRef.stop()
    }

    "teach you how to respond with different kinds of responses" in {

      val echoActorRef = actorOf[EchoActor]
      echoActorRef.start()
      case object Ping
      for (msg <- List("ping", "pong", "pang", "pung", 4711, null, Ping)) {
        (echoActorRef ? msg).get must be(msg)
      }
      echoActorRef.stop()
    }

    "BONUS: steach you how to handle the absense of a sender" in {
      //TODO: Create an ActorRef pointing to an instance of your EchoActor and start it

      //TODO: Send a String message to the ActorRef that doesn't have any sender (HINT from akka docs: def !(message: Any)(implicit channel: UntypedChannel) )

      //TODO: Send another String message to the ActorRef and make sure you get the expected response

      //TODO: Stop the ActorRef
    }

    "My Bonus" in {
      val doublerActor = actorOf[DoublerActor]
      val reverseActor = actorOf(new ReverseActor(doublerActor))
      doublerActor.start()
      reverseActor.start()

      (reverseActor ? "sirap").get must  be ("paris paris")

       doublerActor.stop()
      reverseActor.stop()
    }
  }
}
