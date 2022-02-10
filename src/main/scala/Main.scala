package org.hanlonjohn23

import ArgParser._
import GeoResponseToLocationConverter._
import HttpRequester.GeocoderResponseBody
import LeastDistanceCalculator.getClosestCities

object Main extends App {
  def run(arguments: Arguments): Unit = {
    // Split passed argument on pipe character `|` & trim whitespace
    val cities: Seq[String] = arguments.cities.split("\\|").map(_.trim)

    // Ensure that at least two cities have been passed
    cities match {
      case Seq(single) => throw new IllegalArgumentException("Could not compare cities - Provide more than two cities")
      case _ =>
    }

    // Send Request to Geocoder service for passed args
    val response: Seq[GeocoderResponseBody] = cities.map(HttpRequester.geoCoderGetRequest)

    // Extract relevant location data from Geocoder response
    val locations: Seq[Location] = response.map(_.toLocation)

    // Calculate which two cities are geographically closest
    val closestCities: Distance = getClosestCities(locations)

    val location1 = closestCities.location1
    val location2 = closestCities.location2

    // Report results to user
    print(s"${location1.city}, ${location1.state} & ${location2.city}, ${location2.state} are closest\n")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
