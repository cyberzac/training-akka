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
 * Exercise 8 - "Magical and balanced"
 *
 * In this exercise we will use mixin composition
 * and learn to create actors within actors as well
 * as route messages to achieve a load-balancing effect
 * for a set of actors.
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import akka.event.EventHandler
import akka.routing._
import java.io.Serializable
import JobRelated._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestKit
import akka.util.duration._
import akka.routing.Routing._

object JobRelated {
  //Defines a Task to be performed, that returns Any
  trait Work extends Serializable { def perform(): Any }

  //A Work definition that returns the sum of the supplied numbers
  case class SumSequence(numbers: Seq[BigInt]) extends Work {
    def perform() = numbers.sum
  }

  //This message tells a Worker that he should stop
  case object StopWorking

  //A Worker performs Tasks and sends back the result
  class Worker extends Actor {
    def receive = {
      case w: Work =>
        EventHandler.info(this, "%s starts to perform work of type %s" format (self, w.getClass.getSimpleName))
        self tryReply (self, w.perform())
        EventHandler.info(this, "%s stops to perform work of type %s" format (self, w.getClass.getSimpleName))
      case StopWorking => self.stop
    }
  }

  //Using a trait to implement the default behavior of a Foreman,
  trait ForemanBehavior { this: Actor =>
    def receive: Receive = {
      case w: Work => forwardWorkToAWorker(w)
    }

    //Don't implement this here, this is a part of the external contract for ForemanBehavior
    def forwardWorkToAWorker(w: Work): Unit
  }

  //It must be wizardry to be able to conjure new actors out of thin air
  trait Wizardry extends ForemanBehavior { this: Actor =>
    def conjureNewWorker(): ActorRef = actorOf[Worker].start() //TODO: Implement this to create and start a Worker

    def forwardWorkToAWorker(w: Work) {
      //TODO: Use conjureNewWorker() and send the Work to that worker, preserving the origin of the work, if any

      //TODO: Then tell the worker to stop working
    }
  }

  //Loadbalancing certainly has its uses!
  trait Loadbalancing extends ForemanBehavior { this: Actor =>
    //This shouldn't be implemented here, it should be supplied by the class mixing this trait in
    def numberOfWorkers: Int

    //TODO: create something that will hold workers to be forwarded to in a load-balanced fashion
    
    def forwardWorkToAWorker(w: Work) {
      //TODO: forward the incoming work to one of your workers, preserving the origin of the work, if any
    }
  }
}

//This is our wizard foreman, he can conjure new workers out of thin air and send work to them
class WizardForeman extends Actor with ForemanBehavior with Wizardry

//This is our loadbalancing foreman, he will distribute work in a balanced way to a pool of workers
//TODO: Make it possible to specify number of workers when creating a LoadbalancingForeman and make
//it a regular class instead of an abstract class
class LoadbalancingForeman(var numberOfWorkers: Int) extends Actor with ForemanBehavior with Loadbalancing

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.ForemanSpec
 */
@RunWith(classOf[JUnitRunner])
class ForemanSpec extends AkkaTrainingTest with TestKit { //TODO: We will be needing the TestKit here

  "Exercise8" should {
    "teach you how to create new actors inside other actors" in {
      //TODO: Create and start a WizardForeman

      //TODO: Send N number of SumSequence work to the foreman (using !),
      //      write a test that verifies that all work was responded to,
      //      responses were from unique worker actors,
      //      meaning that the wizard created new ones for each workload.
      //      Also verify that all the results came back as expected. 
      //HINT: map to (ActorRef, BigInt) to get the  results as expected 
    }

    "teach you to how to load-balance work between a finite amount of actors" in {
      //TODO: Create and start a LoadbalancingForeman with 10 workers

      //TODO: Send N number of SumSequence work to the foreman (using !),
      //      write a test that verifies that all work was repsonded to,
      //      responses were from 10 worker actors,
      //      meaning that the loadbalancer reused the workers.
      //      Also verify that all the results came back as expected.
      //HINT: map to (ActorRef, BigInt) to get the  results as expected
    }
  }
}
