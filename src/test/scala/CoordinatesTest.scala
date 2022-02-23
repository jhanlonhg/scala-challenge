package org.hanlonjohn23

import io.circe.Decoder
import io.circe.parser.decode
import org.scalatest.funsuite.AnyFunSuite

class CoordinatesTest extends AnyFunSuite {
  // Fixtures
  val geocoderResponseBody: String =
    """
      |{
      |	"standard": {
      |		"staddress": {},
      |		"stnumber": {},
      |		"prov": "CA",
      |		"city": "Los Angeles",
      |		"postal": {},
      |		"confidence": "0.8"
      |	},
      |	"longt": "-118.303441",
      |	"latt": "34.040462"
      |}
      |""".stripMargin

  test("A response from Geocoder can be converted into a Coordinates object") {
    implicit val locationDecoder: Decoder[Coordinates] = CoordinatesHelper.coordinateDecoder

    val location = Coordinates(
      latitude = 34.040462, longitude = -118.303441
    )
    assert(decode[Coordinates](geocoderResponseBody) == Right(location))
  }
}
