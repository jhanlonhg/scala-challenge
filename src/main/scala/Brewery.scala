package org.hanlonjohn23

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

case class Brewery(
                    name: String,
                    street: String,
                    city: String,
                    state: String,
                    coordinates: Option[Coordinates],
                  )

case class ResolvedBrewery(
                            name: String,
                            coordinates: Coordinates
                          )


object BreweryHelper {
  implicit class CoordinatelessBrewery(brewery: Brewery) {
    def toResolvedBrewery: ResolvedBrewery = {
      ResolvedBrewery(
        name = brewery.name,
        coordinates = brewery.coordinates.getOrElse{
          HttpRequester.geocoderGetRequest(s"${brewery.street} ${brewery.city} ${brewery.state}")
        }
      )
    }
  }

  val breweryDecoder: Decoder[Brewery] = new Decoder[Brewery] {
    override final def apply(c: HCursor): Result[Brewery] = {
      for {
        name <- c.downField("name").as[String]
        street <- c.downField("street").as[String]
        city <- c.downField("city").as[String]
        state <- c.downField("state").as[String]
        latitude <- c.downField("latitude").as[Double]    // TODO: This decoding currently produces NaN instead of None
        longitude <- c.downField("longitude").as[Double]  // TODO: This decoding currently produces NaN instead of None
      } yield {
        Brewery(name, street, city, state, Option(Coordinates(latitude, longitude)))
      }
    }
  }

}