object PairsProcessor {

    /**
     * Calculate the pairs cohesion pairs
     * @param pairs the list of list of consistent pairs
     * @param neighborhoodSize the size of nearest neighbours
     * @param threshold in instruction of exercise we haven't got information about threshold parameter,
     * but it is very simple, to understand - it's difficult to say about the number of neighbours,
     * but fraction of all got sens here
     * @return
     */
    fun consistentPairs(
            pairs: List<Pair<Point, Point>>,
            neighborhoodSize: Int,
            threshold: Double
    ): List<Pair<Point, Point>> = pairs.filter {

        val neighboursOfFirstWithFriends =
                neighborsWithFriends(it.first, pairs - it, neighborhoodSize)
        val neighboursOfSecond = getNeighbors(it.second, (pairs - it).map { it.second }, neighborhoodSize)
        val consistentNeighboursCount = neighboursOfFirstWithFriends.count {
            neighboursOfSecond.contains(it.second)
        }
        consistentNeighboursCount.toDouble() / neighborhoodSize >= threshold
    }


    private fun neighborsWithFriends(
            point: Point,
            pairs: List<Pair<Point, Point>>,
            neighborhoodSize: Int
    ) = pairs
            .map { Pair(it, point.distanceSquare(it.first)) }
            .sortedBy { it.second }
            .take(neighborhoodSize)
            .map { it.first }

    /**
     * Return the nearest (neighborhoodSize) neighbourhoods (by square distance - only by place on picture)
     * @param point base point
     * @param pairs list with pairs
     * @param neighborhoodSize size of neighbourhoods
     * @return list of nearest points
     */
    private fun getNeighbors(point: Point, pairs: List<Point>, neighborhoodSize: Int) =
            pairs
                    .map { Pair(it, point.distanceSquare(it)) }
                    .sortedBy { it.second }
                    .take(neighborhoodSize)
                    .map { it.first }
}
