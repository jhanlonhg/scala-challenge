package org.hanlonjohn23
import ArgParser.{Arguments, parser}

object Defaults {
  val GEO_URL = "https://foo.bar"
}

object Main extends App {

  def run(arguments: Arguments): Unit = {
    val cities: Seq[String] = cityParser(arguments)

    val geoQuery: Json = buildQuery(cities)

    val response: Seq[Json] = sentRequests(geoQuery, Defaults.GEO_URL)

    val citiesLatLong: Seq[CityLatLong] = getCityLatLongs(response)

    val closestCities: (String, String) = getClosestCities(citiesLatLong)

    print()

  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
