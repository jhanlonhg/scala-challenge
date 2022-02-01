package org.hanlonjohn23
import ArgParser.{Arguments, parser}

import scala.annotation.tailrec

object Main extends App {
  @tailrec
  def recursiveSum(sum: Int = 0, valuesToSum: Seq[Int]): Int = {
    if(valuesToSum.isEmpty) {
      sum
    } else {
      recursiveSum(sum + valuesToSum.head, valuesToSum.tail)
    }
  }

  def run(arguments: Arguments): Unit = {
    val sum = recursiveSum(valuesToSum = arguments.addends)
    println(s"Sum: ${sum}")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
