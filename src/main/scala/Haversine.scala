package org.hanlonjohn23

import scala.math._

/**
 * Since I am not a big math person, this code is more or less copied from
 * https://titanwolf.org/Network/Articles/Article?AID=ecce95a3-c9d7-438c-bbd8-720d172e238f
 */
object Haversine extends DistanceGenerator {
  val EARTH_MEAN_RADIUS = 6371 // Kilometers

  override def generateDistance(locationPair: (Coordinates, Coordinates)): Double = {
    val (location1, location2) = locationPair

    // Calculate Haversine distance
    calculateHaversine(location1, location2)
  }

  private def calculateHaversine(location1: Coordinates, location2: Coordinates): Double = {
    val latDelta = (location2.latitude - location1.latitude).toRadians
    val longDelta = (location2.longitude - location1.longitude).toRadians

    /*
      a = hav(Δlat) + hav(Δlon) ⋅ cos(lat2) ⋅ cos(lat1)
      c = 2 ⋅ asin(√a)
      d = R ⋅ c
      Where R is the Earth's Radius (in km)
        and d is the distance between the two points (in km)
     */
    val a = hav(latDelta) + hav(longDelta) * cos(location2.latitude.toRadians) * cos(location1.latitude.toRadians)
    val c = 2 * asin(sqrt(a))

    EARTH_MEAN_RADIUS * c
  }

  private def hav(angle: Double): Double = {
    // hav(θ) = sin²(θ/2)
    pow(sin(angle / 2), 2)
  }
}
