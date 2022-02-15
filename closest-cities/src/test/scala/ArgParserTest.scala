package org.hanlonjohn23

import ArgParser._
import org.scalatest.funsuite.AnyFunSuite

class ArgParserTest extends AnyFunSuite {
  test("ArgParser should correctly parse properly formatted input") {
    val args = Seq("--cities", "Los Angeles, CA | Chicago, IL | New York City, NY")
    val parsedArgs = Some(Arguments(Seq("Los Angeles, CA", "Chicago, IL", "New York City, NY")))

    assert(parser.parse(args, Arguments()) == parsedArgs)
  }
}
