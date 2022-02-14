package org.hanlonjohn23

import scala.annotation.tailrec
import scala.math.{pow, sqrt}
import Haversine._

private case class Distance(
                     location1: Location,
                     location2: Location,
                     length: Double
                   ) {
  def toLocationTuple: (Location, Location) = {
    (location1, location2)
  }
}

object LeastDistanceCalculator {
  @tailrec
  def generateDistinctPairs[A](list: Seq[A], results: Seq[(A, A)] = Nil): Seq[(A, A)] = {
    def generatePairsWithHead(head: A, tail: Seq[A]): Seq[(A, A)] = {
      tail.map((head, _))
    }

    list match {
      case Nil => results
      case head +: tail => generateDistinctPairs(tail, results ++: generatePairsWithHead(head, tail))
    }
  }

  def getClosestCities(locations: Seq[Location]): (Location, Location) = {
    // Generate unique pairs of all locations
    generateDistinctPairs(locations)
      // Generate Distance objects using Location pairs
      .map(generateDistance)
      // Get Distance with smallest length
      .reduce((x,y) => getMinDistance(x, y))
      .toLocationTuple
  }

  private def generateDistance(locationPair: (Location, Location)): Distance = {
    val (location1, location2) = locationPair

    // Calculate Haversine distance
    val distanceBetween: Double = calculateHaversine(location1, location2)

    Distance(
      location1 = location1,
      location2 = location2,
      length = distanceBetween
    )
  }

  private def generatePythagoreanDistance(locationPair: (Location, Location)): Distance = {
    val (location1, location2) = locationPair

    // Calculate Pythagorean distance
    val distanceBetween: Double = sqrt(pow(location1.longitude - location2.longitude, 2) + pow(location1.latitude - location2.latitude, 2))

    Distance(
      location1 = location1,
      location2 = location2,
      length = distanceBetween
    )
  }

  private def getMinDistance(distance1: Distance, distance2: Distance): Distance = {
    if (distance1.length < distance2.length) {
      distance1
    } else {
      distance2
    }
  }
}