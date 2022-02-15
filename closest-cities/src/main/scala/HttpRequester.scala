package org.hanlonjohn23

import io.circe.Decoder.Result
import io.circe._
import io.circe.parser._
import scalaj.http._

object Defaults {
  val GEOCODER_URL = "https://geocoder.ca"
  val GEOCODER_AUTH: String = sys.env("GEOCODER_AUTH")
}

object HttpRequester {
  implicit val locationDecoder: Decoder[Location] = new Decoder[Location] {
    final def apply(c: HCursor): Result[Location] = {
      for {
        city <- c.downField("standard").downField("city").as[String]
        state <- c.downField("standard").downField("prov").as[String]
        latitude <- c.downField("latt").as[Double]
        longitude <- c.downField("longt").as[Double]
      } yield {
        Location(city, state, latitude, longitude)
      }
    }
  }

  def geoCoderGetRequest(city: String): Location = {
    val response = Http(Defaults.GEOCODER_URL)
      .param("auth", Defaults.GEOCODER_AUTH)
      .param("locate", city)
      .param("json", "1") // Request JSON response
      .asString

    decode[Location](response.body) match {
      case Left(error) => println(response); throw error // TODO: Handle this failure case more gracefully
      case Right(response) => response
    }
  }
}