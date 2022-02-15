package org.hanlonjohn23

import math._

/**
 * Since I am not a big math person, this code is more or less copied from
 * https://titanwolf.org/Network/Articles/Article?AID=ecce95a3-c9d7-438c-bbd8-720d172e238f
 */
object Haversine {
  val EARTH_MEAN_RADIUS = 6371  // Kilometers

  private def hav(angle: Double): Double = {
    // hav(θ) = sin²(θ/2)
    pow(sin(angle/2),2)
  }

  def calculateHaversine(location1: Location, location2: Location): Double = {
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

}
