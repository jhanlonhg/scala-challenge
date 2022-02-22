package org.hanlonjohn23

import ArgParser._
import BreweryHelper.{UnResolvedBrewery, getClosestBreweries}

object Main extends App {
  def run(arguments: Arguments): Unit = {
    // Get breweries located in city
    val breweries: Seq[Brewery] = new BrewApi().findBreweriesIn(arguments.city)

    // Get coordinates for breweries without coordinates
    val resolvedBreweries: Seq[ResolvedBrewery] = breweries.filter(_.isResolvable).map(_.toResolvedBrewery)

    // Calculate which two breweries are geographically closest
    val closestBreweries: (ResolvedBrewery, ResolvedBrewery) = getClosestBreweries(resolvedBreweries)

    val (brewery1, brewery2) = closestBreweries

    // Report results to user
    print(s"${brewery1.name} and ${brewery2.name} are the two closest breweries in ${arguments.city}")
  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case None =>
  }
}
