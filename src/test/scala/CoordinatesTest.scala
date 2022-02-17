package org.hanlonjohn23

import io.circe.parser.decode
import io.circe.Decoder
import org.scalatest.funsuite.AnyFunSuite

class CoordinatesTest extends AnyFunSuite {
  test("A response from Geocoder can be converted into a Location object") {
    implicit val locationDecoder: Decoder[Coordinates] = HttpRequesterOld.coordinateDecoder

    val geocoderResponseBody =
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

    val location = Coordinates(
      latitude = 34.040462, longitude = -118.303441
    )
    assert(decode[Coordinates](geocoderResponseBody) == Right(location))
  }
}
