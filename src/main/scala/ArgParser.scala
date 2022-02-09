package org.hanlonjohn23

object ArgParser {
  case class Arguments(cities: String = "")

  val parser = new scopt.OptionParser[Arguments]("java -jar q02.jar") {
    opt[String]('c', "cities")
      .required()
      .valueName("Quote-wrapped, pipe-separated list of city and states")
      .text("e.g., \"Los Angeles, CA |  Chicago, IL | New York City, NY\"")
      .action((value, arguments) => arguments.copy(cities = value))
  }
}
