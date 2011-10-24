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
 * Exercise 12 - "The Chattering"
 *
 * In this exercise we will collaborate with others to create a simple, distributed, chat.
 * We will learn how to start the remoting, how to shut it down, how to register server-managed
 * services and how to send messages to actors running on other machines.
 *
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

sealed trait ChatMessage
case class Message(msg: String) extends ChatMessage
case object GetChatLog extends ChatMessage

class ChatActor(id: String) extends Actor {
  self.id = id
  var chatLog = Vector[String]()

  def receive = {
    case Message(msg) =>
      //TODO: append the message to the chatLog
      //TODO: (optional) print out all the messages so far
    case GetChatLog =>
      //TODO: reply with the chatLog
  }
}
/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.ChatSpec
 */
@RunWith(classOf[JUnitRunner])
class ChatSpec extends AkkaTrainingTest {
  "Exercise12" should {
    "teach you how to start the remote server" in {
      //TODO: start the remote server on an address of your choice

      //TODO: verify that the remote server is running

      //TODO: verify that the remote server is running on the address you specified
    }

    "teach you how to stop the remote server" in {
      //TODO: shut down the remote server

      //TODO: verify that the remote server is not running any more
    }

    "teach you how register and use a remote actor" in {
      try {
        //TODO: start the remote server on an address of your choice

        //TODO: register the ChatActor as a remote actor on the server

        //TODO: imagine we are suddenly on the client side: 
        //      now, get a reference to the remote ChatActor and call it chat 

        // Uncomment when you have the chat ActorRef

        //TODO: get the chat log of messages and call it a chatLog

        // Uncomment when you have the chatLog
      } finally {
        Actor.remote.shutdown
      }
    }
  }
}
