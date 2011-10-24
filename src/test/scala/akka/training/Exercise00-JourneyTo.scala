/*
 * Copyright 2011 Typesafe Inc.
 * 
 * This work is based on the original contribution of WeigleWilczek.
 * 
 * Unless otherwise agreed, training materials may only be used for
 * educational and reference purposes by individual named participants
 * in a training course offered by Typesafe or a Typesafe training partner.
 * Unauthorized reproduction, redistribution, or use of this material is prohibited.
 */
package akka.training

/**
 * Exercise 0- "The Journey to Akka"
 *
 * In this exercise we will refresh our Scala skills starting with
 * basic OO and ending up on for-comprehensions and pattern matching
 *
 * Go towards the look for the JourneryPlannerSpec lower down
 * and follow each part of the exercise and complete the TODOs
 *
 * Let's go!
 */

import akka.training.support._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

//This is our not entirely correctly implemented Time class
case class Time(hours: Int = 0, minutes: Int = 1) {
  //pretend to be heavy calculation, therefore lazy
  lazy val asMinutes: Int = 60 * hours + minutes

  override val toString = "%02d:%02d".format(hours, minutes)

  def minus(that: Time): Int = asMinutes - that.asMinutes
}

//The Time companion object can go here

//Our Train info 
sealed trait TrainInfo {
  def id: String
}

//A WifiInfo Trait here goes here

//This will need to be extended 
case class Ice(id: String) 
case class Re(id: String) 

//A simple station with a name
case class Station(name: String)

//Our Train implementation, with some info and a schedule
case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  val stations: Seq[Station] = ???
}

//Our yet-to-be-finished JourneyPlanner will help us plan our journey 
class JourneyPlanner(trains: Set[Train]) {
  val stations: Set[Station] = ???

  //HINT: Use filter to figure out which trains stops at a specific Station
  def trainsAt(station: Station): Set[Train] = ???

  //HINT: Use for comprehensions to implement this
  def stopsAt(station: Station): Set[(Time, Train)] = ???
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.JournerySpec
 */
@RunWith(classOf[JUnitRunner])
class JourneySpec extends AkkaTrainingTest {
  "Excercise0" should {
    "teach you about some of the basics in Scala" in {
      //TODO: Create a IMMUTABLE instance, time, of the Time class with 10hours and 10minutes

      //TODO: Create a MUTABLE instance, variableTime, of the Time class 

      //TODO: Change the variableTime to something else

      //TODO: Create default parameters for minutes and hours and create a default time instance

      //TODO: Now make the Time class a case class and 
      //      create a new instance, caseTime, of the case class with 10hours and 10minutes

      //TODO: Uncomment and observe that the first time and the case class time works 
      //      since Time is now a case class
      //time must equal(caseTime)

      //TODO: Implement a companion object to the class Time that has a function 
      // 	  creates an instance of Time fromMinutes and check that it works 
      //Time.fromMinutes(90) must equal(Time(1, 30))
    }

    "teach you about operator overloading" in {
      //TODO: Uncomment what you have below
      /*
      val time1 = Time(10, 10)
      val time2 = Time(20, 11)
      */
      
      //TODO: Implement the minus method and uncomment 
      /*
      val time3 = Time.fromMinutes(time2 - time1)
      time3 must equal(Time(10, 1))
      */
    }

    "teach you about using traits" in {
      //TODO: Extend the Ice case class with TrainInfo   

      //TODO: Create a Trait called WifiInfo with a concrete method hasWifi that returns true

      //TODO: Extend the Re case class with TrainInfo and WifiInfo  

      //TODO: Uncomment to check that Ice and Re classes does what you expect them to do
      /*
      val ice: TrainInfo = Ice("1337")
      ice.id must be("1337")
      val re = Re("tR41n1n6")
      re.hasWifi must be(true)
      */
    }

    "teach you about basic pattern matching" in {
      //Half way there - keep hAkking!
      
      //TODO: Uncomment what you have below
      /*
      val reInfo: TrainInfo = Re("tR41n1n6")
      val iceInfo: TrainInfo = Ice("c001")
      val exIce = Ice("extract me")
      val infos = List(exIce, iceInfo, reInfo)
      */
      
      //TODO: Use _one_ pattern matching extraction that i
      // 	  can extract the id from 
      // 	  from either one of the vals above 
      
      //TODO: Use pattern matching to get the head of the list
      //      and check that it works by uncommenting what you 
      //      have below:
      /*
      val extractedHead = infos match ...
      extractedHead.get must be(exIce)
      */
    }

    //********SOME VALs TO HELP YOU IN THE EXERCISES BELOW*********
    //TODO: Uncomment some stations
    /* 
    val lausanneStation = Station("Lausanne")
    val bernStation = Station("Bern")
    val genevaStation = Station("Geneva")
    */
    
    //TODO: Uncomment the trains that stops at Lausann station
    /*
    val trainingTrain = Train(Re("tR41n1n6"), List((Time(1, 2), lausanneStation)))
    val coolTrain = Train(Ice("cool"), List((Time(4, 2), lausanneStation), ((Time(5, 1), bernStation))))
    */
    
    //TODO: Uncomment all our trains
    /*val trains = Set(
    
      trainingTrain,
      coolTrain,
      Train(Re("1337"), List((Time(5, 2), genevaStation))),
      Train(Ice("6164 7r41n"), List((Time(4, 2), bernStation))))
     */
    
    //TODO: Uncomment our JourneyPlanner instance to be used below
    /*
    val journeyPlanner = new JourneyPlanner(trains)
    */
    //*****************  

    "teach you about functional programming on data structures" in {
      //You are almost done - keep going!

      //TODO: On the Train class implement a val, stations, that returns all the 
      // 	  stations for a given Train (in the right order).
      // 	  To check that it is right, just uncomment what you below
      //coolTrain.stations must be(Seq(lausanneStation, bernStation))

      //TODO: On the JourneyPlanner: implement a val, stations, that 
      //      contains the stations that the JourneyPlanner has
      //journeyPlanner.stations must be(Set(lausanneStation, bernStation, genevaStation))

      //TODO: Implement the trainsAt method in the JoruneyPlanner
      //journeyPlanner.trainsAt(lausanneStation) must be(Set(trainingTrain, coolTrain))
    }

    "teach you about for comprehensions" in {
      //Last one: finish this and you are ready for Akka!!

      //TODO: Implement stopsAt methods in the JourneyPlanner that gives
      // 	  says when the trains stops at a station 
      //HINT: This exercise teaches you about for comprehensions
      //Check you got it right by uncommenting the line below
      //journeyPlanner.stopsAt(lausanneStation) must be(Set((Time(1, 2), trainingTrain), (Time(4, 2), coolTrain)))

      //TADA: You are done! Congrats
    }

  }
}
