package org.hanlonjohn23
import scalaj.http._
import io.circe._
import io.circe.parser._
import io.circe.generic.semiauto._

object Defaults {
  val GEOCODER_URL = "https://geocoder.ca"
  val GEOCODER_AUTH: String = sys.env("GEOCODER_AUTH")

}

object HttpRequester {
  case class StandardObject(
                             staddress: JsonObject,
                             stnumber: JsonObject,
                             prov: String,
                             city: String,
                             postal: JsonObject,
                             confidence: String
                           )

  case class GeocoderResponseBody(
                                   standard: StandardObject,
                                   longt: String,
                                   latt: String,
                                   remaining_credits: String
                                 )


  def geoCoderGetRequest(city: String): GeocoderResponseBody = {
    val response = Http(Defaults.GEOCODER_URL)
      .param("auth", Defaults.GEOCODER_AUTH)
      .param("locate", city)
      .param("json", "1")   // Request JSON response
      .asString

    implicit val standardDecoder: Decoder[StandardObject] = deriveDecoder[StandardObject]
    implicit val gcResponseDecoder: Decoder[GeocoderResponseBody] = deriveDecoder[GeocoderResponseBody]

    decode[GeocoderResponseBody](response.body) match {
      case Left(error) => println(response); throw error   // TODO: Handle this failure case more gracefully
      case Right(response) => response
    }
  }
}