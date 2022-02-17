package org.hanlonjohn23

import io.circe.Decoder
import io.circe.parser.decode
import org.scalatest.funsuite.AnyFunSuite

class BreweryTest extends AnyFunSuite {
  // Fixtures
  val breweryWithoutCoordinates = """{
                                    |	"name": "Figueroa Mountain Brewing - Santa Barbara",
                                    |	"street": "137 Anacapa St Ste F",
                                    |	"city": "Santa Barbara",
                                    |	"state": "California",
                                    |	"country": "United States",
                                    |	"longitude": null,
                                    |	"latitude": null
                                    |}""".stripMargin

  val breweryWithCoordinates = """{
                                 |	"name": "Pure Order Brewing Co",
                                 |	"street": "410 N Quarantina St",
                                 |	"city": "Santa Barbara",
                                 |	"state": "California",
                                 |	"longitude": "-119.6864979",
                                 |	"latitude": "34.42351207"
                                 |}""".stripMargin

  implicit val breweryDecoder: Decoder[Brewery] = HttpRequester.breweryDecoder

  test("A brewery without coordinates produces a Coordinate containing None in the Brewery object") {
    val brewery = decode[Brewery](breweryWithoutCoordinates) match {
      case Left(error) => throw error // TODO: Handle this failure case more gracefully
      case Right(brewery) => brewery
    }

    assert(brewery == Brewery(
      name = "Figueroa Mountain Brewing - Santa Barbara",
      street = "137 Anacapa St Ste F",
      city = "Santa Barbara",
      state = "California",
      coordinates = None
    ))
  }
}
