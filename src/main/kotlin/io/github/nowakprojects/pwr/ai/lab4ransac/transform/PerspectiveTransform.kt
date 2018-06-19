import heuristics.Heuristics
import heuristics.VerySimpleHeuristics
import org.ejml.simple.SimpleMatrix
import transform.Transform

class PerspectiveTransform(heuristics: Heuristics = VerySimpleHeuristics) : Transform(heuristics) {

    override val pointsCount = 4

    override fun model(pairs: List<Pair<Point, Point>>): SimpleMatrix? {
        val selectedPairs = heuristics.selectedPairs(pairs, pointsCount)
        val point1OfFirst = selectedPairs[0].first
        val point2OfFirst = selectedPairs[1].first
        val point3OfFirst = selectedPairs[2].first
        val point4OfFirst = selectedPairs[3].first
        val point1OfSecond = selectedPairs[0].second
        val point2OfSecond = selectedPairs[1].second
        val point3OfSecond = selectedPairs[2].second
        val point4OfSecond = selectedPairs[3].second
        val firstMatrix = SimpleMatrix(
                8, 8, true,
                doubleArrayOf(
                        point1OfFirst.x, point1OfFirst.y, 1.0, 0.0, 0.0, 0.0, -point1OfSecond.x * point1OfFirst.x, -point1OfSecond.x * point1OfFirst.y,
                        point2OfFirst.x, point2OfFirst.y, 1.0, 0.0, 0.0, 0.0, -point2OfSecond.x * point2OfFirst.x, -point2OfSecond.x * point2OfFirst.y,
                        point3OfFirst.x, point3OfFirst.y, 1.0, 0.0, 0.0, 0.0, -point3OfSecond.x * point3OfFirst.x, -point3OfSecond.x * point3OfFirst.y,
                        point4OfFirst.x, point4OfFirst.y, 1.0, 0.0, 0.0, 0.0, -point4OfSecond.x * point4OfFirst.x, -point4OfSecond.x * point4OfFirst.y,
                        0.0, 0.0, 0.0, point1OfFirst.x, point1OfFirst.y, 1.0, -point1OfSecond.y * point1OfFirst.x, -point1OfSecond.y * point1OfFirst.y,
                        0.0, 0.0, 0.0, point2OfFirst.x, point2OfFirst.y, 1.0, -point2OfSecond.y * point2OfFirst.x, -point2OfSecond.y * point2OfFirst.y,
                        0.0, 0.0, 0.0, point3OfFirst.x, point3OfFirst.y, 1.0, -point3OfSecond.y * point3OfFirst.x, -point3OfSecond.y * point3OfFirst.y,
                        0.0, 0.0, 0.0, point4OfFirst.x, point4OfFirst.y, 1.0, -point4OfSecond.y * point4OfFirst.x, -point4OfSecond.y * point4OfFirst.y
                )
        )
        val secondMatrix = SimpleMatrix(
                8, 1, true,
                doubleArrayOf(
                        point1OfSecond.x,
                        point2OfSecond.x,
                        point3OfSecond.x,
                        point4OfSecond.x,
                        point1OfSecond.y,
                        point2OfSecond.y,
                        point3OfSecond.y,
                        point4OfSecond.y
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
        val g = vector[6]
        val h = vector[7]
        return SimpleMatrix(
                3, 3, true,
                doubleArrayOf(
                        a, b, c,
                        d, e, f,
                        g, h, 1.0
                )
        )
    }
}
