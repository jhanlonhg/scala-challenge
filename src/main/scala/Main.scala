package org.hanlonjohn23

import ArgParser._
import LeastDistanceCalculator.getClosestCities

object Main extends App {
  def run(arguments: Arguments): Unit = {
    val cities: Seq[String] = arguments.cities

    // Send Request to Geocoder service for passed args
    val locations: Seq[Location] = cities.map(HttpRequester.geoCoderGetRequest)

    // Calculate which two cities are geographically closest
    val closestCities: (Location, Location) = getClosestCities(locations)

    val (location1, location2) = closestCities

    // Report results to user
    print(s"${location1.city}, ${location1.state} & ${location2.city}, ${location2.state} are closest\n")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
