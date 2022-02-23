package org.hanlonjohn23.models

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}
import org.hanlonjohn23.distanceUtils.LeastDistanceCalculator.{generateDistance, generateDistinctPairs}
import org.hanlonjohn23.apis.GeoApi
import org.hanlonjohn23.distanceUtils.{DistanceGenerator, Haversine}

case class Brewery(
                    name: String,
                    address: Address,
                    coordinates: Option[Coordinates],
                  )

case class ResolvedBrewery(
                            name: String,
                            coordinates: Coordinates
                          )


object BreweryHelper {
  implicit class UnResolvedBrewery(brewery: Brewery) {
    def toResolvedBrewery: ResolvedBrewery = {
      ResolvedBrewery(
        name = brewery.name,
        coordinates = brewery.coordinates.getOrElse {
          new GeoApi().getCoordinatesFor(brewery.address)
        }
      )
    }

    def isResolvable: Boolean = {
      (brewery.address.street, brewery.coordinates) match {
        case ("", None) => false
        case _ => true
      }
    }


  }

  val breweryDecoder: Decoder[Brewery] = new Decoder[Brewery] {
    override final def apply(c: HCursor): Result[Brewery] = for {
      name <- c.downField("name").as[String]
      maybeStreet <- c.downField("street").as[Option[String]]
      city <- c.downField("city").as[String]
      state <- c.downField("state").as[String]
      maybeLatitude <- c.downField("latitude").as[Option[Double]]
      maybeLongitude <- c.downField("longitude").as[Option[Double]]
    } yield {

      val coordinates = (maybeLatitude, maybeLongitude) match {
        case (Some(lat), Some(long)) => Some(Coordinates(lat, long))
        case _ => None
      }

      val street = (maybeStreet) match {
        case Some(street) => street
        case None => ""
      }

      Brewery(name, Address(street, city, state), coordinates)
    }
  }

  def getClosestBreweries(breweries: Seq[ResolvedBrewery]): (ResolvedBrewery, ResolvedBrewery) = {
    case class DistanceBetween(
                                brewery1: ResolvedBrewery,
                                brewery2: ResolvedBrewery,
                                distance: Double
                              ) {
      def toBreweryTuple: (ResolvedBrewery, ResolvedBrewery) = {
        (brewery1, brewery2)
      }
    }

    def getMinBreweryDistance(between1: DistanceBetween, between2: DistanceBetween): DistanceBetween = {
      if (between1.distance < between2.distance) {
        between1
      } else {
        between2
      }
    }

    implicit val distanceGenerator: DistanceGenerator = Haversine
    // Generate unique pairs of all locations
    generateDistinctPairs(breweries)
      // Generate Distance objects using Location pairs
      .map((breweryPair) => DistanceBetween(
        breweryPair._1,
        breweryPair._2,
        generateDistance(breweryPair._1.coordinates, breweryPair._2.coordinates))
      )
      // Get Distance with smallest length
      .reduce((x, y) => getMinBreweryDistance(x, y))
      // Return tuple of Resolved Breweries
      .toBreweryTuple
  }
}