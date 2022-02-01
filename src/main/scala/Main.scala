package org.hanlonjohn23
import ArgParser.{Arguments, parser}

import scala.annotation.tailrec

object Main extends App {
  @tailrec
  def recursiveSum(valuesToSum: Seq[Int], sum: Int = 0): Int = {
    valuesToSum match {
      case Nil => sum
      case _ => recursiveSum(valuesToSum.tail, sum + valuesToSum.head)
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
