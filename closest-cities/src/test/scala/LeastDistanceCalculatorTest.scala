package org.hanlonjohn23

import org.scalatest.funsuite.AnyFunSuite

class LeastDistanceCalculatorTest extends AnyFunSuite {
  // Fixtures
  val losAngeles: Location = Location(city = "Los Angeles", state = "CA", latitude = 34.040462, longitude = -118.303441)
  val oxnard: Location = Location(city = "Oxnard", state = "CA", latitude = 34.195306, longitude = -119.184146)
  val sanDiego: Location = Location(city = "San Diego", state = "CA", latitude = 32.806472, longitude = -117.131995)

  val honolulu: Location = Location(city = "Honolulu", state = "HI", latitude = 21.315603, longitude = -157.858093)
  val tokyo: Location = Location(city = "Tokyo", state = "都", latitude = 35.652832, longitude = 139.839478)
  val newYork: Location = Location(city = "New York City", state = "NY", latitude = 40.730610, longitude = -73.935242)

  test("generateDistinctPairs should create a list of tuple distinct pairs from the passed list") {
    assert(LeastDistanceCalculator.generateDistinctPairs(Seq("a", "b", "c"))
      == List(("a", "b"), ("a", "c"), ("b", "c")) )
  }

  test("getClosestCities should return a tuple containing the closest Location objects") {
    assert(LeastDistanceCalculator.getClosestCities(Seq(losAngeles, sanDiego, oxnard))
      == (losAngeles, oxnard) )
  }

  test("getClosestCities should handle cities on opposite sides of the 180th meridian") {
    assert(LeastDistanceCalculator.getClosestCities(Seq(honolulu, tokyo, newYork))
    == (honolulu, tokyo) )
  }
}
