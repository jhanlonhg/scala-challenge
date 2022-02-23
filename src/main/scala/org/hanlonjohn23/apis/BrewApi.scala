package org.hanlonjohn23.apis

import io.circe.Decoder
import org.hanlonjohn23._
import org.hanlonjohn23.http.{HttpRequester, ScalaJHttpRequester}
import org.hanlonjohn23.models.{Brewery, BreweryHelper, Coordinates, CoordinatesHelper}

object BrewApiDefaults {
  val OPEN_BREWERY_URL = "https://api.openbrewerydb.org/"
}

class BrewApi extends Api[Brewery] {
  override def httpRequester: HttpRequester = new ScalaJHttpRequester(s"${BrewApiDefaults.OPEN_BREWERY_URL}/breweries")

  implicit val coordinateDecoder: Decoder[Coordinates] = CoordinatesHelper.coordinateDecoder
  implicit val breweryDecoder: Decoder[Brewery] = BreweryHelper.breweryDecoder

  def findBreweriesIn(city: String): Seq[Brewery] = {
    val params: Seq[(String, String)] = Seq(
      ("by_city", city)
    )
    fetchAndDecode(params)
  }
}