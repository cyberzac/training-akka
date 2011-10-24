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
 * Exercise 7 - "The Mailman is a trait-or"
 *
 * In this exercise we will learn how to use Scala traits
 * to create modular behaviors that will allow you to
 * mix in behaviors into your actors.
 * We will also learn how to pass messages around and preserve
 * the sender of a message.
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import MailmanActor._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Below is the blueprint of our soon to be MailmanActor and RecipientActor,
 * this is where you will implement the behavior of the actor.
 * We will also learn to use 'forward' to preserve the original
 * sender of messages.
 *
 * Underneath the RecipientActor-class is the unit-test you will create
 * to verify your implementation.
 *
 * To verify that your code works as expected run:
 * sbt > test-only akka.training.MailmanActorSpec
 */

case class Parcel(recipient: Uuid, contents: AnyRef)

object MailmanActor {
  sealed trait MailmanMessage
  case class RegisterRecipient(recipient: ActorRef) extends MailmanMessage
  case class DeregisterRecipient(recipient: ActorRef) extends MailmanMessage
  case class ReturnToSender(parcel: Parcel) extends MailmanMessage

  trait RegistrationBehavior { actor: Actor =>
    var registeredRecipients = Map[Uuid, ActorRef]()

    //TODO: Implement this method
    def handleRegistrations: Receive = ???
    //RegisterRecipient messages should register recipients
    //DeregisterRecipient messages should deregister recipients

    //TODO: Implement this method
    def findRecipientFor(recipientUuid: Uuid): Option[ActorRef] = ???
  }

  trait ParcelManagement { actor: Actor =>

    def findRecipientFor(recipientUuid: Uuid): Option[ActorRef] //You will not need to implement this

    //TODO: Implement the following method
    def handleParcel: Receive = ???
    //Parcel should be forwarded to the correct recipient (if found) else reply with a ReturnToSender
  }
}

class MailmanActor { //TODO: Make this into an Actor that utilizes your traits
  //TODO: Implement the receive method
}

object RecipientActor {
  sealed trait RecipientMessage
  case class ThanksForThePresent(contents: AnyRef) extends RecipientMessage
}

//This is our recipient of the parcels 
class RecipientActor  { //TODO: Make this an Actor
  import RecipientActor._

  //Our received Parcels
  var receivedParcels: List[Parcel] = Nil 
  
  //TODO: Implement receive to only receive parcels meant for this actor,
  //      and put the parcels at the head of the 'receivedParcels' list and respond
  //      with a ThanksForThePresent message with the contents of the parcel
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.MailmanActorSpec
 */
@RunWith(classOf[JUnitRunner])
class MailmanActorSpec extends AkkaTrainingTest {
  import MailmanActor._
  import RecipientActor._

  "Exercise7" should {
    "teach you how to route messages to other actors and preserve the sender" in {
      //TODO: Create and start a MailmanActor

      //TODO: Create and start a RecipientActor

      //TODO: Create a parcel, addressed to the recipient, to the mailman and make sure you get a ReturnToSender with the same parcel

      //TODO: Register the recipient to the mailman

      //TODO: Send a parcel, addressed to the recipient, to the mailman and
      // make sure you get a ThanksForThePresent back with the expected contents

      //TODO: Now deregister the recipient from the mailman

      //TODO: Then verify that sending a parcel, addressed to the recipient, to the mailman yields the expected ReturnToSender

      //TODO: Stop both the mailman and the recipient
    }
  }
}
