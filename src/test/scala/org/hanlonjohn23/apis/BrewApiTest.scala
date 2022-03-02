package org.hanlonjohn23.apis

import org.hanlonjohn23.http.HttpRequester
import org.hanlonjohn23.models.Address
import org.scalatest.funsuite.AnyFunSuite



class BrewApiTest extends AnyFunSuite {
  // Fixtures
  def mockHttpRequester: HttpRequester = (params: Seq[(String, String)]) => {
      val byCityParam = params.find(_._1 == "by_city")
      assert(byCityParam.contains(("by_city", "Anytown")))
      s"""
         |[{
         |	"name": "The Foo Bar",
         |	"street": "1337 Baz Ave",
         |	"city": "${byCityParam.getOrElse(("by_city", "Wrong City"))._2}",
         |	"state": "California",
         |	"country": "United States",
         |	"longitude": null,
         |	"latitude": null
         |}]""".stripMargin
  }

  test("findBreweriesIn should return results from that city") {
    val response = new BrewApi(mockHttpRequester).findBreweriesIn("Anytown")

    val address = response.head.address

    assert(address.contains(Address("1337 Baz Ave", "Anytown", "California")))
  }
}

