package org.hanlonjohn23.apis

import org.hanlonjohn23.http.HttpRequester
import org.hanlonjohn23.models.Address
import org.scalatest.funsuite.AnyFunSuite

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

class BrewApiTest extends AnyFunSuite {
  // Initialize BrewApi for testing
  val brewApi = new BrewApi(new MockHttpRequester("http://notarealurl.com"))

  test("findBreweriesIn should return results from that city") {
    val breweries = brewApi.findBreweriesIn("Anytown")

    val address = breweries.head.address

    assert(address.contains(Address("1337 Baz Ave", "Anytown", "California")))
  }
}

