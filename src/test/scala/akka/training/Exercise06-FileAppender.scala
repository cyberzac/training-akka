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
 * Exercise 6 - "File it under asynchronous"
 *
 * In this exercise we will learn how to use 'become' and 'unbecome' -
 * one of the cornerstones in the Actor Model. We will also learn how
 * to create an Actor that can asynchronously write a simple log.
 *
 * Let's go!
 */
import akka.training.support._
import akka.actor._
import akka.actor.Actor._
import java.io._
import java.nio.channels._
import java.nio._
import akka.testkit.TestActorRef
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Below is the blueprint of our soon to be FileAppenderActor,
 * this is where you will implement the behavior of the actor.
 *
 * Underneath the FileAppenderActor-class is the unit-test you will create
 * to verify your implementation.
 *
 * To verify that your code works as expected run:
 * sbt > test-only akka.training.FileAppenderActorActorSpec
 */

object FileAppenderActor {
  sealed trait FileAppenderMessage
  //Opens the supplied file, you cannot open a new file before closing the old,
  //it should only be received when a file isn't Open
  case class Open(filePath: String) extends FileAppenderMessage

  //Append should only be received when a file is Open
  case class Append(text: String) extends FileAppenderMessage

  //Close should only be received when a file is Open
  case object Close extends FileAppenderMessage

  //WhichFile can be sent at any time, and will be responded to with CurrentFile
  case object WhichFile extends FileAppenderMessage
  case class CurrentFile(filePath: Option[String]) extends FileAppenderMessage

  //This is the response to all Open, Append and Close messages that is successful
  case object OK extends FileAppenderMessage
}

class FileAppenderActor  { //TODO: First, we need to make sure this is extends Actor
  import FileAppenderActor._

  def write(channel: FileChannel, text: String) = { 
    val buf = ByteBuffer.allocate(48)
    buf.clear()
    buf.put(text.getBytes())

    buf.flip()

    while (buf.hasRemaining()) {
      channel.write(buf)
    }
  }

  //This field should have Some(File) if there is a current file, or None if there is none
  var currentFile: Option[File] = None

  //This field should have Some(FileChannel) if there is a current file, or None if there is none
  var currentChannel: Option[FileChannel] = None
  var fos: Option[FileOutputStream] = None 

  def closeFile() = {
    currentChannel.foreach(_.close())
    fos.foreach(_.close())
    currentChannel = None
    currentFile = None
  }

  //TODO: Write a method named 'opened' that returns a Receive (PartialFunction[Any,Unit] that receives the following
  //      message types: Append, WhichFile and Close
  //
  def opened: Receive = ???

  //TODO: Write a method named 'closed' that returns a Receive (PartialFunction[Any, Unit] that receives the following
  //      message types: Open and WhichFile
  def closed: Receive = ???

  //TODO: Then we need to implement the receive-method so that the actor initially has the 'closed' behavior
  
  //BONUS: Implement a postStop method that cleans up
}

/**
 * The following test verifies that the functionality
 * of the code you've written works as expected,
 * you can execute the test by simply running it in Eclipse or
 * by typing this in sbt :
 *
 * test-only akka.training.FileAppenderActorSpec
 */
@RunWith(classOf[JUnitRunner])
class FileAppenderActorSpec extends AkkaTrainingTest {
  import FileAppenderActor._

  //Creates a temporary file that is deleted after the test, can be useful!
  def withTempFile(fun: File => Unit) {
    val file = File.createTempFile("foo", "bar")
    try {
      fun(file)
    } finally {
      file.delete
    }
  }

  "Exercise6" should {

    "teach you how to use become and unbecome to change behavior" in {
      //TODO: Create and start a FileAppenderActor *TestActorRef*

      //TODO: Make sure that the TestActorRef does not currently support the Append or Close messages

      //TODO: Verify that the Actor supports the Open and WhichFile messages

      //TODO: Test that the TestActorRef initially has no open file

      //TODO: Test that the TestActorRef has no open file after sending it an "Open"-message with a path that doesn't point to a file

      withTempFile { f =>
        //TODO: Test that the TestActorRef points to the correct file after sending it an "Open"-message with a path that points to a file

        //TODO: Now verify that the Actor doesn't support the Open message

        //TODO: Then verify that the Actor now supports the Append, WhichFile and Close messages

      }
      //TODO: Stop the ActorRef
    }

    "teach you how you can make IO asynchronous with Actors" in {
      //TODO: Create and start a FileAppenderActor *ActorRef* (this time the real one)

      withTempFile { f =>
        //TODO: Tell the FileAppender to open with a correct file

        //TODO: Create and start 2 EchoActor ActorRefs

        //TODO: Send 10 Append-messages with unique texts in each, and set the sender of the messages to be the FileAppender

        //TODO: Wait for the echo Actors to complete their requests 

        //TODO: Send a Close message to the FileAppender and await it to be processed

        //TODO: Stop the EchoActors and the FileAppender

        //TODO: Verify that each of the 10 messages are inside the file that the FileAppender was appending to
      }
    }

    "BONUS: make the actor automatically clean itself up and becoming 'closed' when stopped" in {
    }

    "BONUS: use akka.actor.Actor.spawn to send lots of text in concurrently to the same appender" in {

    }
  }
}
