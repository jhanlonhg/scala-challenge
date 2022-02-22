package org.hanlonjohn23

import scopt.OptionParser

object ArgParser {
  val parser: OptionParser[Arguments] = new scopt.OptionParser[Arguments]("java -jar q03.jar") {
    opt[String]('c', "city")
      .required()
      .valueName("Quote-wrapped name of city that contains breweries")
      .text("e.g., \"Los Angeles\"")
      .action((value, arguments) => arguments.copy(city = value))
  }

  case class Arguments(city: String = "")
}
