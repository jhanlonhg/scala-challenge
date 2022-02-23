package org.hanlonjohn23

import org.scalatest.funsuite.AnyFunSuite

class LeastDistanceCalculatorTest extends AnyFunSuite {
  // Fixtures
  val losAngeles: Coordinates = Coordinates(latitude = 34.040462, longitude = -118.303441)
  val oxnard: Coordinates = Coordinates(latitude = 34.195306, longitude = -119.184146)
  val sanDiego: Coordinates = Coordinates(latitude = 32.806472, longitude = -117.131995)

  val honolulu: Coordinates = Coordinates(latitude = 21.315603, longitude = -157.858093)
  val tokyo: Coordinates = Coordinates(latitude = 35.652832, longitude = 139.839478)
  val newYork: Coordinates = Coordinates(latitude = 40.730610, longitude = -73.935242)

  test("generateDistinctPairs should create a list of tuple distinct pairs from the passed list") {
    assert(LeastDistanceCalculator.generateDistinctPairs(Seq("a", "b", "c"))
      == List(("a", "b"), ("a", "c"), ("b", "c")))
  }

//  test("getClosestCities should return a tuple containing the closest Location objects") {
//    assert(LeastDistanceCalculator.
//      getClosestBreweries(Seq(losAngeles, sanDiego, oxnard))
//      == (losAngeles, oxnard))
//  }
//
//  test("getClosestCities should handle cities on opposite sides of the 180th meridian") {
//    assert(LeastDistanceCalculator.getClosestBreweries(Seq(honolulu, tokyo, newYork))
//      == (honolulu, tokyo))
//  }
}
