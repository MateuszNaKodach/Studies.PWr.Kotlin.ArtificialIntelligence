package heuristics

import Point

/**
 * Created by {USER}
 */
class SimpleSquareHeuristic(smallR: Double, bigR: Double) : SimpleHeuristics(smallR, bigR) {

    /**
     * Method to return the distance between two points
     * @param firstPoint first point
     * @param secondPoint second point
     * @return distance
     */
    override fun getDistance(firstPoint: Point, secondPoint: Point) = firstPoint.distanceSquare(secondPoint)

}