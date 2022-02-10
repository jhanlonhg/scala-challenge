package org.hanlonjohn23

import HttpRequester.{GeocoderResponseBody, StandardObject}
import io.circe.JsonObject
import org.scalatest.funsuite.AnyFunSuite
import GeoResponseToLocationConverter.GeocoderResponseToLocation

class LocationTest extends AnyFunSuite {
  test("A GeocoderResponseBody object can be converted into a Location object") {
    val geocoderResponseBody = GeocoderResponseBody(
      StandardObject(staddress = JsonObject(), stnumber = JsonObject(), prov = "CA", city = "Los Angeles", postal = JsonObject(), confidence = "0.8"),
      longt = "-118.303441",
      latt = "34.040462",
      remaining_credits = "-23"
    )
    val location = Location(
      city = "Los Angeles", state = "CA", latitude = 34.040462, longitude = -118.303441
    )
    assert(geocoderResponseBody.toLocation == location)
  }
}
