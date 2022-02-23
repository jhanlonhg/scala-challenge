package org.hanlonjohn23

import ArgParser.{Arguments, parser}

import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream

class ArgParserTest extends AnyFunSuite {
  test("ArgParser should correctly parse properly formatted input") {
    val args = Seq("--city", "Los Angeles")
    val parsedArgs = Some(Arguments("Los Angeles"))

    assert(parser.parse(args, Arguments()) == parsedArgs)
  }

  test("ArgParser should print an error to console when not provided a city") {
    val args = Seq("-c")
    val errCapture = new ByteArrayOutputStream

    Console.withErr(errCapture) {
      parser.parse(args, Arguments())
    }

    assert(errCapture.toString.contains("Error: Missing value after -c"))
  }
}
