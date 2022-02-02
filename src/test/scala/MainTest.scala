package org.hanlonjohn23;

import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite {
    test("recursiveSum should return 0 if given an empty list") {
        assert(Main.recursiveSum(List.empty) == 0)
    }

    test("recursiveSum should return value if given a single value") {
        assert(Main.recursiveSum(Seq(1)) == 1)
    }

    test("recursiveSum should return a sum if given two values") {
        assert(Main.recursiveSum(Seq(1,2)) == 3)
    }

    test("recursiveSum should return a sum if given a range of values") {
        assert(Main.recursiveSum(Seq(1,2,3,4,5)) == 15)
    }

    test("recursiveSum should return a sum if given an set of values") {
        assert(Main.recursiveSum(Seq(14,28,29,7,7,15)) == 100)
    }
}
