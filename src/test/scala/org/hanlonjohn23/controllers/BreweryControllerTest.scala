package org.hanlonjohn23.controllers

import org.hanlonjohn23.models.{Address, Brewery, Coordinates, ResolvedBrewery}
import org.scalatest.funsuite.AnyFunSuite

class BreweryControllerTest extends AnyFunSuite {
  // Fixtures
  val maybeAddress: Option[Address] = Some(Address("1 Foo St", "Santa Barbara", "California"))
  val maybeCoordinates: Option[Coordinates] = Some(Coordinates(34.418813, -119.683188))

  val goodBrewery: Brewery = Brewery(name = "FooBar", address = maybeAddress, coordinates = maybeCoordinates)
  val coordinatesBrewery: Brewery = Brewery(name = "FooBar", address = None, coordinates = maybeCoordinates)
  val addressBrewery: Brewery = Brewery(name = "FooBar", address = maybeAddress, coordinates = None)
  val badBrewery: Brewery = Brewery(name = "FooBar", address = None, coordinates = None)

  val resolvedBrewery: ResolvedBrewery = ResolvedBrewery(name = "FooBar", coordinates = Coordinates(34.418813, -119.683188))

  def addressToCoordinates(address: Address): Coordinates = { Coordinates(34.418813, -119.683188) }

  val closeResolvedBrewery: ResolvedBrewery = ResolvedBrewery("The Baz Room", Coordinates(34.422092488906934, -119.70343894243503))
  val farResolvedBrewery: ResolvedBrewery = ResolvedBrewery("The O.G.", Coordinates(34.43170562812181, -119.84449914963267))

  val resolvedBreweries: Seq[ResolvedBrewery] = Seq(resolvedBrewery, closeResolvedBrewery, farResolvedBrewery)

  test("A brewery with all properties will resolve to a ResolvedBrewery") {
    assert(BreweryController.resolveBrewery(goodBrewery, addressToCoordinates).contains(resolvedBrewery))
  }

  test("A brewery with only coordinates will resolve to a ResolvedBrewery") {
    assert(BreweryController.resolveBrewery(coordinatesBrewery, addressToCoordinates).contains(resolvedBrewery))
  }

  test("A brewery with only an address will resolve to a ResolvedBrewery") {
    assert(BreweryController.resolveBrewery(addressBrewery, addressToCoordinates).contains(resolvedBrewery))
  }

  test("A brewery without coordinates or address will not resolve") {
    assert(BreweryController.resolveBrewery(badBrewery, addressToCoordinates).isEmpty)
  }

  test("getClosestBreweries should return a tuple containing the two closest breweries from a collection of ResolvedBreweries") {
    assert(BreweryController.getClosestBreweries(resolvedBreweries) == (resolvedBrewery, closeResolvedBrewery))
  }
}
