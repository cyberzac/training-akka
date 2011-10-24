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
 * Exercise 5  - "A Kind Of Service"
 *
 * In this exercise we will about Typed Actors
 *
 * Let's go!
 */

import akka.training.support._
import akka.actor.TypedActor
import scala.collection.immutable.TreeSet
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.dispatch.Future
import scala.collection.immutable.TreeMap
import akka.actor.ActorInitializationException

//A kind of User
case class User(val name: String, val born: Int)

//This is one of the services that we will be implementing
trait RegistrationService {
  def register(user: User*): Unit
  def get(name: String): Option[User]
  def findByPrefix(prefix: String): Future[List[User]]
}

class RegistrationServiceImpl { //TODO: Create a RegistrationService implementation and make it a Typed Actor
  //our trusty database
  var userDb = TreeMap[String, User]()

}

//A simple util for prefix searching. You can use this in the findByPrefix method if you want
object Prefix {
  def find[T](prefix: String, db: TreeMap[String, T]): List[T] =
    if (prefix.trim.isEmpty) List.empty
    else (db from prefix filter { _._1.startsWith(prefix) }).map { _._2 }.toList
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by typing this in sbt:
 *
 * test-only akka.training.TypedActorSpec
 */
@RunWith(classOf[JUnitRunner])
class TypedActorSpec extends AkkaTrainingTest {
  "Exercise5" should {
    "teach you how to use Typed Actors" in {
      //TODO: Create an instance of the RegistrationService

      //TODO: Register some users

      //TODO: Check that you can get the users you expect 

      //TODO: Use the long running async method and check that the results are what you expect

      //TODO: Stop the service
    }
  }
}
