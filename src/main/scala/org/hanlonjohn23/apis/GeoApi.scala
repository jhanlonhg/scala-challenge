package org.hanlonjohn23.apis

import org.hanlonjohn23.http.{HttpRequester, ScalaJHttpRequester}
import org.hanlonjohn23.models.{Address, Coordinates}

object GeoApiDefaults {
  val GEOCODER_URL = "https://geocoder.ca"
  val GEOCODER_AUTH: String = sys.env("GEOCODER_AUTH")
  val SUBADDRESS_REGEX: String = " (Ste|Apt|Unit) [a-zA-Z0-9]{1,4}"
}

class GeoApi(
              override val httpRequester: HttpRequester = new ScalaJHttpRequester(GeoApiDefaults.GEOCODER_URL)
            ) extends Api[Coordinates] {

  protected implicit class GeoApiAddress(address: Address) {
    def deriveStreetAddress: String = {
      s"${address.removeSubAddress().street} ${address.city}, ${address.state}"
    }

    def removeSubAddress(): Address = {
      address.copy(street = address.street.replaceAll(GeoApiDefaults.SUBADDRESS_REGEX, ""))
    }
  }

  def getCoordinatesFor(address: Address): Coordinates = {
    val params: Seq[(String, String)] = Seq(
      ("auth", GeoApiDefaults.GEOCODER_AUTH),
      ("locate", address.deriveStreetAddress),
      ("json", "1") // Request JSON response
    )

    getAndDecode(params)
  }
}
