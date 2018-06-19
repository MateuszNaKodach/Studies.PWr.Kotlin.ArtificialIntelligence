package transform

import heuristics.Heuristics
import heuristics.VerySimpleHeuristics
import Point
import org.ejml.simple.SimpleMatrix

class AffineTransform(heuristics: Heuristics = VerySimpleHeuristics) : Transform(heuristics) {

    override val pointsCount = 3

    override fun model(pairs: List<Pair<Point, Point>>): SimpleMatrix? {
        val selectedPairs = heuristics.selectedPairs(pairs, pointsCount)
        val point1OfFirst = selectedPairs[0].first
        val point2OfFirst = selectedPairs[1].first
        val point3OfFirst = selectedPairs[2].first
        val point1OfSecond = selectedPairs[0].second
        val point2OfSecond = selectedPairs[1].second
        val point3OfSecond = selectedPairs[2].second
        val firstMatrix = SimpleMatrix(
                6, 6, true,
                doubleArrayOf(
                        point1OfFirst.x, point1OfFirst.y, 1.0, 0.0, 0.0, 0.0,
                        point2OfFirst.x, point2OfFirst.y, 1.0, 0.0, 0.0, 0.0,
                        point3OfFirst.x, point3OfFirst.y, 1.0, 0.0, 0.0, 0.0,
                        0.0, 0.0, 0.0, point1OfFirst.x, point1OfFirst.y, 1.0,
                        0.0, 0.0, 0.0, point2OfFirst.x, point2OfFirst.y, 1.0,
                        0.0, 0.0, 0.0, point3OfFirst.x, point3OfFirst.y, 1.0
                )
        )
        val secondMatrix = SimpleMatrix(
                6, 1, true,
                doubleArrayOf(
                        point1OfSecond.x,
                        point2OfSecond.x,
                        point3OfSecond.x,
                        point1OfSecond.y,
                        point2OfSecond.y,
                        point3OfSecond.y
                )
        )
        return model(firstMatrix, secondMatrix)
    }

    override fun model(vector: SimpleMatrix): SimpleMatrix {
        val a = vector[0]
        val b = vector[1]
        val c = vector[2]
        val d = vector[3]
        val e = vector[4]
        val f = vector[5]
        return SimpleMatrix(
                3, 3, true,
                doubleArrayOf(
                        a, b, c,
                        d, e, f,
                        0.0, 0.0, 1.0
                )
        )
    }
}
