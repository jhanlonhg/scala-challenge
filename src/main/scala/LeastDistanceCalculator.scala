package org.hanlonjohn23

import scala.annotation.tailrec
import scala.math.{pow, sqrt}

case class Distance(
                     location1: Location,
                     location2: Location,
                     length: Double
                   )

object LeastDistanceCalculator {
  @tailrec
  def generateDistinctPairs[A](list: Seq[A], results: Seq[(A, A)] = Nil): Seq[(A, A)] = {
    def generatePairs(list: Seq[A]): Seq[(A, A)] = {
      val head +: tail = list
      tail.map((head, _))
    }

    list match {
      case Nil => results
      case _ => generateDistinctPairs(list.tail, results ++: generatePairs(list))
    }
  }

  def getClosestCities(locations: Seq[Location]): Distance = {
    // Generate unique pairs of all locations
    generateDistinctPairs(locations)
      // Generate Distance objects using Location pairs
      .map(generateDistance)
      // Get Distance with smallest length
      .reduce((x,y) => getMinDistance(x, y))
  }

  private def generateDistance(locationPair: (Location, Location)): Distance = {
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
    // TODO: I am sure there is a pattern that can resolve this issue in a cleaner fashion
    val minDistanceDouble = distance1.length min distance2.length

    minDistanceDouble match {
      case distance1.length => distance1
      case distance2.length => distance2
    }
  }
}