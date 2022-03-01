package org.hanlonjohn23.controllers

import org.hanlonjohn23.distanceUtils.LeastDistanceCalculator.{generateDistance, generateDistinctPairs}
import org.hanlonjohn23.distanceUtils.{DistanceGenerator, Haversine}
import org.hanlonjohn23.models.{Address, Brewery, Coordinates, ResolvedBrewery}

object BreweryController {
  def resolveBrewery(brewery: Brewery, f: Address => Coordinates): Option[ResolvedBrewery] = {
    brewery match {
      case Brewery(_, _, Some(coordinates)) =>
        Some(ResolvedBrewery(
          brewery.name,
          coordinates
        ))
      case Brewery(_, Some(address), None) =>
        Some(ResolvedBrewery(
          brewery.name,
          f(address)
        ))
      case _ => None
    }
  }

  def getClosestBreweries(breweries: Seq[ResolvedBrewery]): (ResolvedBrewery, ResolvedBrewery) = {
    case class DistanceBetween(
                                brewery1: ResolvedBrewery,
                                brewery2: ResolvedBrewery,
                                distance: Double
                              ) {
      def toBreweryTuple: (ResolvedBrewery, ResolvedBrewery) = {
        (brewery1, brewery2)
      }
    }

    def getMinBreweryDistance(between1: DistanceBetween, between2: DistanceBetween): DistanceBetween = {
      if (between1.distance < between2.distance) {
        between1
      } else {
        between2
      }
    }

    implicit val distanceGenerator: DistanceGenerator = Haversine

    // Generate unique pairs of all locations
    generateDistinctPairs(breweries)
      // Generate Distance objects using Location pairs
      .map(breweryPair => DistanceBetween(
        breweryPair._1,
        breweryPair._2,
        generateDistance(breweryPair._1.coordinates, breweryPair._2.coordinates))
      )
      // Get Distance with smallest length
      .reduce((x, y) => getMinBreweryDistance(x, y))
      // Return tuple of Resolved Breweries
      .toBreweryTuple
  }
}
