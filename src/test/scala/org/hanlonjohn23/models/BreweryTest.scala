package org.hanlonjohn23.models

import io.circe.Decoder
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

  val breweryWithCoordinates: String =
    """{
      |	"name": "Pure Order Brewing Co",
      |	"street": "410 N Quarantina St",
      |	"city": "Santa Barbara",
      |	"state": "California",
      |	"longitude": "-119.6864979",
      |	"latitude": "34.42351207"
      |}""".stripMargin

  implicit val breweryDecoder: Decoder[Brewery] = BreweryHelper.breweryDecoder

  test("A brewery without coordinates produces a Brewery object with the coordinate property containing None") {
    val brewery = decode[Brewery](breweryWithoutCoordinates) match {
      case Left(error) => throw error // TODO: Handle this failure case more gracefully
      case Right(brewery) => brewery
    }

    assert(brewery == Brewery(
      name = "Figueroa Mountain Brewing - Santa Barbara",
      address = Address(
        street = "137 Anacapa St Ste F",
        city = "Santa Barbara",
        state = "California"
      ),
      coordinates = None
    ))
  }

  test("A brewery with coordinates produces a Brewery object with the coordinate property containing Some[Coordinates]") {
    val brewery = decode[Brewery](breweryWithCoordinates) match {
      case Left(error) => throw error
      case Right(brewery) => brewery
    }
    assert(brewery == Brewery(
      name = "Pure Order Brewing Co",
      address = Address(
        street = "410 N Quarantina St",
        city = "Santa Barbara",
        state = "California"
      ),
      coordinates = Some(Coordinates(34.42351207, -119.6864979))
    ))
  }
}