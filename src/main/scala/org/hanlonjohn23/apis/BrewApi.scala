package org.hanlonjohn23.apis

import org.hanlonjohn23.http.{HttpRequester, ScalaJHttpRequester}
import org.hanlonjohn23.models.Brewery

object BrewApiDefaults {
  val OPEN_BREWERY_URL = "https://api.openbrewerydb.org/"
}

class BrewApi(
               override val httpRequester: HttpRequester = new ScalaJHttpRequester(s"${BrewApiDefaults.OPEN_BREWERY_URL}/breweries")
             ) extends Api[Brewery] {

  def findBreweriesIn(city: String): Seq[Brewery] = {
    val params: Seq[(String, String)] = Seq(
      ("by_city", city)
    )
    fetchAndDecode(params)
  }
}
