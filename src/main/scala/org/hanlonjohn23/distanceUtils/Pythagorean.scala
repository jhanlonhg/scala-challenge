package org.hanlonjohn23.distanceUtils

import org.hanlonjohn23.models.Coordinates

import scala.math.{pow, sqrt}

object Pythagorean extends DistanceGenerator {
  override def generateDistance(locationPair: (Coordinates, Coordinates)): Double = {
    val (location1, location2) = locationPair

    // Calculate Haversine distance
    calculatePythagorean(location1, location2)
  }

  private def calculatePythagorean(location1: Coordinates, location2: Coordinates): Double = {
    sqrt(pow(location1.longitude - location2.longitude, 2) + pow(location1.latitude - location2.latitude, 2))
  }
}
