package org.hanlonjohn23.apis

import org.hanlonjohn23.http.HttpRequester
import org.hanlonjohn23.models.Address
import org.scalatest.funsuite.AnyFunSuite

// TODO: Still not sure how I should be testing the HTTP functionality

class MockHttpRequester(url: String) extends HttpRequester {
  override def get(params: Seq[(String, String)]): String = {
    val byCityParam = params.find(param => param._1 == "by_city").getOrElse(("by_city", "Wrong City"))
    s"""
       |[{
       |	"name": "The Foo Bar",
       |	"street": "1337 Baz Ave",
       |	"city": "${byCityParam._2}",
       |	"state": "California",
       |	"country": "United States",
       |	"longitude": null,
       |	"latitude": null
       |}]""".stripMargin
  }
}

class GeoApiTest extends AnyFunSuite {
  // Initialize GeoApi for testing
  val geoApi = new GeoApi(new MockHttpRequester("http://notarealurl.com"))

  // Fixtures
  val cleanAddress: Address = Address(street = "1 N Calle Cesar Chavez", city = "Santa Barbara", state = "CA")
  val addressWithSuite: Address = Address(street = "1 N Calle Cesar Chavez Ste 100", city = "Santa Barbara", state = "CA")
  val addressWithUnit: Address = Address(street = "1 N Calle Cesar Chavez Unit A", city = "Santa Barbara", state = "CA")
  val addressWithApt: Address = Address(street = "1 N Calle Cesar Chavez Apt A23B", city = "Santa Barbara", state = "CA")
  val streetAddress: String = "1 N Calle Cesar Chavez Santa Barbara, CA"

  test("An address object with no sub-address should serialize to itself") {
    assert(geoApi.GeoApiAddress(cleanAddress).getStreetAddress == streetAddress)
  }

  test("An address object with a suite sub-address should serialize without the suite") {
    assert(addressWithSuite.getStreetAddress == streetAddress)
  }

  test("An address object with a unit sub-address should serialize without the unit") {
    assert(addressWithUnit.getStreetAddress == streetAddress)
  }

  test("An address object with an apartment sub-address should serialize without the apartment") {
    assert(addressWithApt.getStreetAddress == streetAddress)
  }


}