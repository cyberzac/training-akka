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
 * Exercise 9 - "Back to the Future"
 *
 * In this exercise we will learn how to use Futures
 * to manage replies and to avoid blocking.
 * We will reuse what we did for exercise5 and add some
 * spice!
 *
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import akka.dispatch.{ Future, Futures }
import akka.training.FutureWork._
import akka.training.JobRelated.{ Work, Worker, SumSequence }
import akka.dispatch.ActorCompletableFuture


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import akka.util.duration._
import akka.testkit.TestKit

object FutureWork {
  //We will use this to introduce failures
  case object FailWork extends Work {
    def perform() = throw new IllegalStateException("expected")
  }

  //We will use this to simulate delays in work processing
  case class DelayedEchoWork(sleepytimeMs: Long, echo: Any) extends Work {
    def perform() = {
      Thread.sleep(sleepytimeMs)
      echo
    }
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.FutureSpec
 */
@RunWith(classOf[JUnitRunner])
class FutureSpec extends AkkaTrainingTest with TestKit { 

  "Exercise9" should {
    "teach you the simplest way to extract the result of a Future" in {
      //TODO: Create and start a Worker actor

      //TODO: Send, using ?, a SumSequence message to the worker and use as[(ActorRef, BigInt)] 
      //      to extract the result

      //TODO: Verify that you get the correct result from the future
      //HINT: Use ._2 to the BigInt only

      //TODO: Stop the worker
    }

    "teach you how you can asynchronously transform the result of a future" in {
      //TODO: Create and start a Worker and an Echo actor

      //TODO: Create a Future by using ? on your worker actor
      //HINT: You will be getting 

      //TODO: On a result (which will be a tuple of (ActorRef, BigInt))
      //      transform the result to a string and
      //      send, using !, the transformed string to the echo actor and
      //      check that you got the expectedMsg within a reasonable amount of time
      //      using the TestKit
      //HINT: You can use "case (_,result) => ..." to extract the result only 
      within(1000 millis) {
        
      }
      
      //TODO: Stop the worker
    }

    "teach you how to handle a failure in a Future" in {
      //TODO: Create and start a Worker and an Echo actor

      //TODO: Send, using ?, a FailWork to the worker

      //TODO: Write some code that examines the future and checks if it failed

      within(100 millis) {
      //TODO: Send, using ?, a FailWork to the worker, and  
      //      on an exception
      //      send, using !, the exception to the Echo Actor and 
      //      verify that you got the expectedMsg within a 
      //      a reasonable amount of time
      }

      //TODO: Send a message and create code that recovers from an 
      //      exception by returning a default value if it is thrown

      //TODO: Stop the worker
    }

    "teach you how to pick the fastest service using Futures" in {
      //TODO: Create and start the WizardForeman actor
      //      The WizardForeman forwards work to new Worker and
      //      therefore avoids *blocking*

      //TODO: Create 2 different futures using ? and DelayedEchoWork with
      //      a sleepytime varying by about a second or two

      //TODO: Await the completion of the first completing Future of the two,
      //      and then immediately verify the response 
      //HINT: Use as[(ActorRef,SOMETHING)] to be get the result tuple
      //      then get._2 to get only the SOMETHING you sent in with your
      //      DelayedEchoWork

      //BONUS: Also verify that only the minimum required waiting was done

      //TODO: Stop the wizardForeman
    }

    "teach you how to await many futures to be completed" in {
      //TODO: Create and start a WizardForeman

      //TODO: Using ?, send 10 DelayedEchoWork with different sleepytimes, and have it echo
      //      to the foreman and generate a list of the resulting futures.
      //HINT: You can use .mapTo[(ActorRef, SOMETHING)] to map a future to the result you are expecting 

      //TODO: Await all the futures, and verify that you got the result you expected in each

      //BONUS: Try sending again and sum the results WITHOUT awaiting 
      //HINT:  Here you would want to create ONE future which you can use 
      //       once the result is ready

      //TODO: Stop wizardForeman
    }

    "teach you how to compose Futures" in {
      //TODO: Create and start a WizardForeman

      //TODO: Send 2 Ints using DelayedEchoWork and add them without waiting
      //HINT: In for-comprehensions you can use (_, f : Int) <- myFuture to 
      //      to map and extract values from a future

      //TODO: Await the newly created Future and verify the results

      //TODO: Stop foreman
    }
  }
}
