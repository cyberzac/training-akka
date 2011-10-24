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
 * Exercise 13 - "Atomic Bank"
 *
 * In this exercise we will learn how to use the STM module in Akka
 * to create transactional behaviour for some accounts in a bank
 *
 * We will learn how to receive messages in Actors and reply with a response.
 *
 * Let's go!
 */

import akka.stm._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.training.support._
import akka.event.EventHandler

object BankStuff {

  class Account(name: String) {
    private val currentAmount = ??? //Create a reference 

    def deposit(amount: BigDecimal): Unit = ??? //TODO: make deposits atomic

    def withdraw(amount: BigDecimal): Unit = ??? //TODO: make withdrawing atomic

    def balance() = ???  //TODO: get the current amount atomically 
  }
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.BankSpec
 */
@RunWith(classOf[JUnitRunner])
class BankSpec extends AkkaTrainingTest {
  import BankStuff._

  "Excercise13" should {
    "teach you " in {
      //TODO: First off, open up 2 new accounts

      //TODO: deposit some money in your account

      //TODO: atomically withdraw some money from one and deposit it to another
      //      then make sure that the state is consistent if a IllegalStateException happens 
      //      between the withdrawal and deposit
    }
  }
}

