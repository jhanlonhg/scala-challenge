package org.hanlonjohn23

import org.hanlonjohn23.apis.{BrewApi, GeoApi}
import org.hanlonjohn23.cli.ArgParser.{Arguments, parser}
import org.hanlonjohn23.controllers.BreweryController
import org.hanlonjohn23.models.{Brewery, ResolvedBrewery}

object Main extends App {
  def run(arguments: Arguments): Unit = {
    // Get breweries located in city
    val breweries: Seq[Brewery] = new BrewApi().findBreweriesIn(arguments.city)

    // Clean raw Brewery models into ResolvedBrewery models
    val resolvedBreweries: Seq[ResolvedBrewery] = breweries.flatMap(BreweryController.resolveBrewery(_, new GeoApi().getCoordinatesFor))

    // Calculate which two breweries are geographically closest
    val closestBreweries: (ResolvedBrewery, ResolvedBrewery) = BreweryController.getClosestBreweries(resolvedBreweries)

    val (brewery1, brewery2) = closestBreweries

    // Report results to user
    print(s"${brewery1.name} and ${brewery2.name} are the two closest breweries in ${arguments.city}")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
