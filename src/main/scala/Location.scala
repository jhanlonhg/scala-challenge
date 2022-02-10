package org.hanlonjohn23

import HttpRequester.GeocoderResponseBody

case class Location(
                     city: String,
                     state: String,
                     latitude: Double,
                     longitude: Double
                   )

object GeoResponseToLocationConverter {
  implicit class GeocoderResponseToLocation(gcrb: GeocoderResponseBody) {
    def toLocation: Location = Location(
      city = gcrb.standard.city,
      state = gcrb.standard.prov,
      latitude = gcrb.latt.toDouble,
      longitude = gcrb.longt.toDouble
    )
  }
}