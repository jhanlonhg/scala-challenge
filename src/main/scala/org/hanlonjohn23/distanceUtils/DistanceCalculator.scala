package org.hanlonjohn23.distanceUtils

import org.hanlonjohn23.models.Coordinates

import scala.annotation.tailrec

trait DistanceGenerator {
  def generateDistance(locationPair: (Coordinates, Coordinates)): Double
}

object LeastDistanceCalculator {
  @tailrec
  def generateDistinctPairs[A](list: Seq[A], results: Seq[(A, A)] = Nil): Seq[(A, A)] = {
    def generatePairsWithHead(head: A, tail: Seq[A]): Seq[(A, A)] = {
      tail.map((head, _))
    }

    list match {
      case Nil => results
      case head +: tail => generateDistinctPairs(tail, results ++: generatePairsWithHead(head, tail))
    }
  }

  def generateDistance(locationPair: (Coordinates, Coordinates))(implicit distanceGenerator: DistanceGenerator): Double = {
    distanceGenerator.generateDistance(locationPair)
  }
}