package org.hanlonjohn23.models

import org.scalatest.funsuite.AnyFunSuite

class AddressTest extends AnyFunSuite {
  // Fixtures
  val cleanAddress: Address = Address(street = "1 N Calle Cesar Chavez", city = "Santa Barbara", state = "CA")
  val addressWithSuite: Address = Address(street = "1 N Calle Cesar Chavez Ste 100", city = "Santa Barbara", state = "CA")
  val addressWithUnit: Address = Address(street = "1 N Calle Cesar Chavez Unit A", city = "Santa Barbara", state = "CA")
  val addressWithApt: Address = Address(street = "1 N Calle Cesar Chavez Apt A23B", city = "Santa Barbara", state = "CA")
  val streetAddress: String = "1 N Calle Cesar Chavez Santa Barbara, CA"

//  test("An address object with no sub-address should serialize to itself") {
//    assert(cleanAddress.getStreetAddress == streetAddress)
//  }
//
//  test("An address object with a suite sub-address should serialize without the suite") {
//    assert(addressWithSuite.getStreetAddress == streetAddress)
//  }
//
//  test("An address object with a unit sub-address should serialize without the unit") {
//    assert(addressWithUnit.getStreetAddress == streetAddress)
//  }
//
//  test("An address object with an apartment sub-address should serialize without the apartment") {
//    assert(addressWithApt.getStreetAddress == streetAddress)
//  }
}
