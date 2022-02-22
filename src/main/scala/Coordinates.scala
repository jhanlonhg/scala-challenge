package org.hanlonjohn23

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

case class Coordinates(
                        latitude: Double,
                        longitude: Double
                      )

object CoordinatesHelper {
  val coordinateDecoder: Decoder[Coordinates] = new Decoder[Coordinates] {
    final def apply(c: HCursor): Result[Coordinates] = {
      for {
        latitude <- c.downField("latt").as[Double]
        longitude <- c.downField("longt").as[Double]
      } yield {
        Coordinates(latitude, longitude)
      }
    }
  }
}