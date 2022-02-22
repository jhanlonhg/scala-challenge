package org.hanlonjohn23

import io.circe.Decoder

object GeoApiDefaults {
  val GEOCODER_URL = "https://geocoder.ca"
  val GEOCODER_AUTH: String = sys.env("GEOCODER_AUTH")
}

class GeoApi extends Api[Coordinates] {
  override def httpRequester: HttpRequester = new ScalaJHttpRequester(GeoApiDefaults.GEOCODER_URL)

  implicit val coordinateDecoder: Decoder[Coordinates] = CoordinatesHelper.coordinateDecoder

  def getCoordinatesFor(address: Address): Coordinates = {
    val params: Seq[(String, String)] = Seq(
      ("auth", GeoApiDefaults.GEOCODER_AUTH),
      ("locate", address.getStreetAddress),
      ("json", "1") // Request JSON response
    )
    getAndDecode(params)
  }
}
