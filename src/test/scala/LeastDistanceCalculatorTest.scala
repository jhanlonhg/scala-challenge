package org.hanlonjohn23

import org.scalatest.funsuite.AnyFunSuite

class LeastDistanceCalculatorTest extends AnyFunSuite {
  // Fixtures
  val losAngeles: Location = Location(city = "Los Angeles", state = "CA", latitude = 34.040462, longitude = -118.303441)
  val oxnard: Location = Location(city = "Oxnard", state = "CA", latitude = 34.195306, longitude = -119.184146)
  val sanDiego: Location = Location(city = "San Diego", state = "CA", latitude = 32.806472, longitude = -117.131995)

  test("generateDistinctPairs should create a list of tuple distinct pairs from the passed list") {
    assert(LeastDistanceCalculator.generateDistinctPairs(Seq("a", "b", "c"))
      == List(("a", "b"), ("a", "c"), ("b", "c")))
  }

  test("getClosestCities should return a distance object with the smallest `length` value") {
    assert(LeastDistanceCalculator.getClosestCities(Seq(losAngeles, sanDiego, oxnard))
      == Distance(losAngeles, oxnard, 0.8942135994050789))
  }
}
