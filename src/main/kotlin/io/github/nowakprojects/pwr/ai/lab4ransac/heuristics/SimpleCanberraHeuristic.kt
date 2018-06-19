package heuristics

import Point

/**
 * The SimpleHeuristic with Canberra distance
 */
class SimpleCanberraHeuristic(smallR: Double, bigR: Double) : SimpleHeuristics(smallR, bigR) {

    /**
     * Method to return the distance between two points
     * @param firstPoint first point
     * @param secondPoint second point
     * @return distance
     */
    override fun getDistance(firstPoint: Point, secondPoint: Point) = firstPoint.distanceCanberra(secondPoint)

}