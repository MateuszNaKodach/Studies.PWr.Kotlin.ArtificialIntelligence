package heuristics

import Point

/**
 * Interface to ransac heuristic
 */
interface Heuristics {

    /**
     * Method to return selected pairs by ransac heuristic
     * @param pairs pairs from witch heuristic select result pairs
     * @param pointsCount the max result count
     * @return selected pairs
     */
    fun selectedPairs(pairs: List<Pair<Point, Point>>, pointsCount: Int): List<Pair<Point, Point>>
}
