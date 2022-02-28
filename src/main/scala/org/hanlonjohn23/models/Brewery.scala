package org.hanlonjohn23.models

import io.circe.{Decoder, HCursor}

case class Brewery(
                    name: String,
                    address: Option[Address],
                    coordinates: Option[Coordinates],
                  )

object Brewery {
  implicit val breweryDecoder: Decoder[Brewery] = (c: HCursor) => for {
    name <- c.downField("name").as[String]
    maybeStreet <- c.downField("street").as[Option[String]]
    maybeCity <- c.downField("city").as[Option[String]]
    maybeState <- c.downField("state").as[Option[String]]
    maybeLatitude <- c.downField("latitude").as[Option[Double]]
    maybeLongitude <- c.downField("longitude").as[Option[Double]]
  } yield {
    val maybeAddress = (maybeStreet, maybeCity, maybeState) match {
      case (Some(street), Some(city), Some(state)) => Some(Address(street, city, state))
      case _ => None
    }

    val maybeCoordinates = (maybeLatitude, maybeLongitude) match {
      case (Some(lat), Some(long)) => Some(Coordinates(lat, long))
      case _ => None
    }

    Brewery(name, maybeAddress, maybeCoordinates)
  }
}