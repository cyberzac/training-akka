package akka.training.support

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, WordSpec}
import org.scalatest.matchers.MustMatchers
import akka.actor._
import akka.testkit.TestKit

abstract class AkkaTrainingTest extends WordSpec
                                   with BeforeAndAfterAll
                                   with BeforeAndAfterEach
                                   with MustMatchers
