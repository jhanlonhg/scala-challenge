package org.hanlonjohn23.models

import io.circe.parser.decode
import org.scalatest.funsuite.AnyFunSuite

class BreweryTest extends AnyFunSuite {
  // Fixtures
  val breweryWithoutCoordinates: String =
    """{
      |	"name": "Figueroa Mountain Brewing - Santa Barbara",
      |	"street": "137 Anacapa St Ste F",
      |	"city": "Santa Barbara",
      |	"state": "California",
      |	"country": "United States",
      |	"longitude": null,
      |	"latitude": null
      |}""".stripMargin

  val breweryWithoutAddress: String =
    """{
      |	"name": "Lawless Brewing",
      |	"street": null,
      |	"city": "Los Angeles",
      |	"state": "California",
      |	"longitude": "-118.2427669",
      |	"latitude": "34.0536834"
      |}""".stripMargin

  val breweryWithoutEither: String =
    """{
      |	"name": "Reel Brew Co.",
      |	"street": null,
      |	"city": "Los Angeles",
      |	"state": "California",
      |	"longitude": null,
      |	"latitude": null
      |}""".stripMargin

  val breweryWithEverything: String =
    """{
      |	"name": "Pure Order Brewing Co",
      |	"street": "410 N Quarantina St",
      |	"city": "Santa Barbara",
      |	"state": "California",
      |	"longitude": "-119.6864979",
      |	"latitude": "34.42351207"
      |}""".stripMargin

  def decoder(breweryJson: String): Brewery = {
    decode[Brewery](breweryJson) match {
      case Left(error) => throw error // TODO: Handle this failure case more gracefully
      case Right(brewery) => brewery
    }
  }

  test("A brewery JSON without coordinates produces a Brewery object with the coordinates property containing None") {
    val brewery = decoder(breweryWithoutCoordinates)

    assert(brewery == Brewery(
      name = "Figueroa Mountain Brewing - Santa Barbara",
      address = Some(Address(
        street = "137 Anacapa St Ste F",
        city = "Santa Barbara",
        state = "California"
      )),
      coordinates = None
    ))
  }

  test("A brewery JSON without a street produces a Brewery object with the address property containing None") {
    val brewery = decoder(breweryWithoutAddress)

    assert(brewery == Brewery(
      name = "Lawless Brewing",
      address = None,
      coordinates = Some(Coordinates(34.0536834, -118.2427669))
    ))
  }

  test("A brewery JSON without coordinates or a street produces a Brewery object with the coordinates & address properties containing None") {
    val brewery = decoder(breweryWithoutEither)

    assert(brewery == Brewery(
      name = "Reel Brew Co.",
      address = None,
      coordinates = None
    ))
  }

  test("A brewery JSON with coordinates & a street produces a Brewery object with defined coordinates & address properties") {
    val brewery = decoder(breweryWithEverything)

    assert(brewery == Brewery(
      name = "Pure Order Brewing Co",
      address = Some(Address(
        street = "410 N Quarantina St",
        city = "Santa Barbara",
        state = "California"
      )),
      coordinates = Some(Coordinates(34.42351207, -119.6864979))
    ))
  }
}