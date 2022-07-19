package org.hanlonjohn23.apis

import org.hanlonjohn23.http.HttpRequester
import org.hanlonjohn23.models.{Address, Coordinates}
import org.scalatest.funsuite.AnyFunSuite

class GeoApiTest extends AnyFunSuite {
  // Fixtures
  val cleanAddress: Address = Address(street = "1 N Calle Cesar Chavez", city = "Santa Barbara", state = "CA")
  val addressWithSuite: Address = Address(street = "1 N Calle Cesar Chavez Ste 100", city = "Santa Barbara", state = "CA")
  val addressWithUnit: Address = Address(street = "1 N Calle Cesar Chavez Unit A", city = "Santa Barbara", state = "CA")
  val addressWithApt: Address = Address(street = "1 N Calle Cesar Chavez Apt A23B", city = "Santa Barbara", state = "CA")

  val streetAddress: String = "1 N Calle Cesar Chavez Santa Barbara, CA"
  val coordinates: Coordinates = Coordinates(34.418822, -119.683188)
  val apiResponse: String =
    """
      |{
      |	"standard": {
      |		"staddress": {},
      |		"stnumber": {},
      |		"prov": "CA",
      |		"city": "Santa Barbara",
      |		"postal": {},
      |		"confidence": "0.8"
      |	},
      |	"longt": "-119.683188",
      |	"latt": "34.418822"
      |}
      |""".stripMargin

  def mockHttpRequester: HttpRequester = (params: Seq[(String, String)]) => {
    assert(params.find(_._1 == "locate").contains(("locate", streetAddress)))
    apiResponse
  }

  test("getAndDecode should send a clean street address to Geocoder.ca when provided a clean Address") {
    // White box test GeoApi functionality as it interacts with HttpRequester
    val response = new GeoApi(mockHttpRequester).getCoordinatesFor(cleanAddress)

    assert(response == coordinates)
  }

  test("getAndDecode should send a clean street address to Geocoder.ca when provided an Address with a suite") {
    // White box test GeoApi functionality as it interacts with HttpRequester
    val response = new GeoApi(mockHttpRequester).getCoordinatesFor(addressWithSuite)

    assert(response == coordinates)
  }

  test("getAndDecode should send a clean street address to Geocoder.ca when provided an Address with an apartment") {
    // White box test GeoApi functionality as it interacts with HttpRequester
    val response = new GeoApi(mockHttpRequester).getCoordinatesFor(addressWithApt)

    assert(response == coordinates)
  }

  test("getAndDecode should send a clean street address to Geocoder.ca when provided an Address with a unit") {
    // White box test GeoApi functionality as it interacts with HttpRequester
    val response = new GeoApi(mockHttpRequester).getCoordinatesFor(addressWithUnit)

    assert(response == coordinates)
  }
}