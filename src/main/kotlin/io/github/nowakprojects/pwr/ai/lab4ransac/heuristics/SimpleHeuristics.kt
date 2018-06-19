package heuristics

import Point

/**
 * This heuristic search the pairs that all the selected pairs are in space between smallR and bigR
 * @param smallR smallest distance between pairs points
 * @param bigR biggest distance between pair points
 */
abstract class SimpleHeuristics(smallR: Double, bigR: Double) : Heuristics {

    /**
     * Minimum square distance
     */
    private val smallSquare = Math.pow(smallR, 2.0)

    /**
     * Maximum square distance
     */
    private val bigSquare = Math.pow(bigR, 2.0)

    /**
     * Finds the point pairs matching
     * @param pairs all points pairs
     * @param pointsCount max size of returned set
     * @return set of selected points pairs
     */
    override fun selectedPairs(pairs: List<Pair<Point, Point>>, pointsCount: Int): List<Pair<Point, Point>> {
        val selectedPairs = mutableListOf<Pair<Point, Point>>()
        val shuffled = pairs.shuffled()
        selectedPairs += shuffled[0]
        var index = 1
        while (index < shuffled.size && selectedPairs.size < pointsCount) {
            val pair = shuffled[index]
            val ok = ok(pair, selectedPairs)
            if (ok) {
                selectedPairs += pair
            }
            index++
        }
        return selectedPairs
    }

    /**
     * Check that pair can be added to set
     * @param pair pair witch we want to add
     * @param selectedPairs all currently selected pairs
     * @return boolean that pair can be added to new set
     */
    private fun ok(pair: Pair<Point, Point>, selectedPairs: MutableList<Pair<Point, Point>>): Boolean =
            selectedPairs.all {
                ok(it.first, pair.first) && ok(it.second, pair.second)
            }

    /**
     * Check that new point pair match to simple pair
     * @param firstPoint pair that we want to add
     * @param secondPoint pair for witch we check new pair
     * @return boolean that two pairs match
     */
    private fun ok(firstPoint: Point, secondPoint: Point): Boolean {
        val distanceSquare = firstPoint.distanceSquare(secondPoint)
        return smallSquare < distanceSquare && distanceSquare < bigSquare
    }

    /**
     * Method to return the distance between two points
     * @param firstPoint first point
     * @param secondPoint second point
     * @return distance
     */
    abstract fun getDistance(firstPoint: Point, secondPoint: Point): Double
}
