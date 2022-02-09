package org.hanlonjohn23
import ArgParser.{Arguments, parser}
import GeoResponseToLocationConverter._

import scala.math.{pow, sqrt}
import org.hanlonjohn23.HttpRequester.GeocoderResponseBody
import org.hanlonjohn23.LocationUtils.getClosestCities
import scalaj.http._

//case class CityLatLong


object Main extends App {
  case class Distance(
                       location1: Location,
                       location2: Location,
                       length: Double
                     )

  def cityParser(cities: String): Seq[String] = {
    // Split String on pipe character `|`
    cities.split("\\|").map(_.trim)
  }

  def sendRequests(geoQuery: String) {
    Http(Defaults.GEOCODER_URL).param("auth",Defaults.GEOCODER_AUTH).param("locate", geoQuery).param("json", "1").asString
  }

  def run(arguments: Arguments): Unit = {
    val cities: Seq[String] = cityParser(arguments.cities)

    val response: Seq[GeocoderResponseBody] = cities.map(HttpRequester.geoCoderGetRequest)

    val citiesLatLong: Seq[Location] = response.map(_.toCityLatLong)

    val closestCities: Distance = getClosestCities(citiesLatLong)

    print(s"${closestCities.location1.city} and ${closestCities.location2.city} are closest")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
