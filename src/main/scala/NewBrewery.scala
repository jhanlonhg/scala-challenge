package org.hanlonjohn23

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

case class Address(
                    street: String,
                    city: String,
                    state: String
                  ) {
  def getStreetAddress: String = {
    s"$street $city, $state"
  }
}

case class NewBrewery(
                       name: String,
                       location: Either[Address, Coordinates]
                     )

object NewBreweryHelper {
  implicit val newBreweryDecoder: Decoder[NewBrewery] = new Decoder[NewBrewery] {
    override final def apply(c: HCursor): Result[NewBrewery] = {
      for {
        name <- c.downField("name").as[String]
        street <- c.downField("street").as[String]
        city <- c.downField("city").as[String]
        state <- c.downField("state").as[String]
        latitude <- c.downField("latitude").as[Double]
        longitude <- c.downField("longitude").as[Double]
      } yield {
        val coordinates = Option(Coordinates(latitude, longitude))

        coordinates match {
          case Some(coordinates) => NewBrewery(name, Right(coordinates))
          case None => NewBrewery(name, Left(Address(street, city, state)))
        }
      }
    }
  }
}
