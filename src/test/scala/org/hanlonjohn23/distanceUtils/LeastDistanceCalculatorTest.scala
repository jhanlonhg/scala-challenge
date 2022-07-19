package org.hanlonjohn23.distanceUtils

import org.hanlonjohn23.models.Coordinates
import org.scalatest.funsuite.AnyFunSuite

class LeastDistanceCalculatorTest extends AnyFunSuite {
  // Fixtures
  val losAngeles: Coordinates = Coordinates(latitude = 34.040462, longitude = -118.303441)
  val sanDiego: Coordinates = Coordinates(latitude = 32.806472, longitude = -117.131995)
  val newYork: Coordinates = Coordinates(latitude = 40.730610, longitude = -73.935242)
  val honolulu: Coordinates = Coordinates(latitude = 21.315603, longitude = -157.858093)
  val tokyo: Coordinates = Coordinates(latitude = 35.652832, longitude = 139.839478)

  test("generateDistinctPairs should create a list of tuple distinct pairs from the passed list") {
    assert(LeastDistanceCalculator.generateDistinctPairs(Seq("a", "b", "c"))
      == List(("a", "b"), ("a", "c"), ("b", "c")))
  }

  // TODO: Are these tests appropriate here? Is there some other way we should test the DistanceGenerators?
  test("generateDistance should return a Double representing relative distance when using the Pythagorean Distance Generator") {
    implicit val distanceGenerator: DistanceGenerator = Pythagorean

    assert(LeastDistanceCalculator.generateDistance((losAngeles, sanDiego)) == 1.7014749633820663)
  }

  test("generateDistance should return a Double representing km distance when using the Haversine Distance Generator") {
    implicit val distanceGenerator: DistanceGenerator = Haversine

    assert(LeastDistanceCalculator.generateDistance((losAngeles, newYork)) == 3947.126093439537)
  }

  test("generateDistance should handle cities on opposite sides of the 180th meridian when using the Haversine Distance Generator") {
    implicit val distanceGenerator: DistanceGenerator = Haversine

    assert(LeastDistanceCalculator.generateDistance((honolulu, tokyo)) < 6200.0)
  }
}
