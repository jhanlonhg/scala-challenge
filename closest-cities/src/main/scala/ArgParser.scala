package org.hanlonjohn23

object ArgParser {
  case class Arguments(cities: Seq[String] = Seq())

  private def parseCities(value: String): Seq[String] = {
    // Split passed argument on pipe character `|` & trim whitespace
    value.split("\\|").map(_.trim)
  }

  private def containsSingleElement[A](seq: Seq[A]): Boolean = {
    seq match {
      case Seq(single) => true
      case _ => false
    }
  }

  val parser = new scopt.OptionParser[Arguments]("java -jar q02.jar") {
    opt[String]('c', "cities")
      .required()
      .valueName("Quote-wrapped, pipe-separated list of city and states")
      .text("e.g., \"Los Angeles, CA | Chicago, IL | New York City, NY\"")
      .validate(value =>
        if (containsSingleElement(parseCities(value))) failure("Could not compare cities - Provide more than two cities.")
        else success )
      .action((value, arguments) => arguments.copy(cities = parseCities(value)))
      }
}
