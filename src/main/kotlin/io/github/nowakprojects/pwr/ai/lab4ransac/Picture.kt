import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * The representation of picture with the keyPoints
 * @param keyPoints list of keyPoints with readImage features
 */
data class Picture(private val keyPoints: List<KeyPoint>) {

    /**
     * Find the pairs of keyPoints matching in other picture
     * @return list of pair with matching keyPoints
     */
    fun keyPointsPairs(picture: Picture): List<Pair<KeyPoint, KeyPoint>> = indexesPairs(picture).map {
        val first = keyPoints[it.first]
        val second = picture.keyPoints[it.second]
        Pair(first, second)
    }

    /**
     * Search the matching pairs of points in pictures
     * @return pairs of index on first picture and second with matching points
     */
    private fun indexesPairs(picture: Picture): List<Pair<Int, Int>> = runBlocking {
        val indexes1 = respectivelyClosestIndexesIn(picture)
        val indexes2 = picture.respectivelyClosestIndexesIn(this@Picture)
        indexes1.mapIndexedNotNull { index, indexOfClosest ->
            pairOrNull(indexes2, indexOfClosest, index)
        }
    }

    /**
     * Check that the best match of point first have the same best match point
     * @return the pair of indexes when points match or null when doesn't
     */
    private fun pairOrNull(indexes2: List<Int>, indexOfClosest: Int, index: Int): Pair<Int, Int>? {
        return if (indexes2[indexOfClosest] == index) {
            Pair(index, indexOfClosest)
        } else {
            null
        }
    }

    /**
     * Calculates the closest indexes for all points on picture
     * @param picture picture where we search closest points
     */
    private suspend fun respectivelyClosestIndexesIn(picture: Picture) = keyPoints.map {
        async {
            findClosestIndex(it, picture.keyPoints)
        }
    }.map { it.await() }

    /**
     * Method find the closest keyPoint to searching keyPoint
     * @param point base point
     * @param keyPoints the list of points
     * @return the index of closest keyPoint from list to base keyPoint
     */
    private fun findClosestIndex(point: KeyPoint, keyPoints: List<KeyPoint>): Int {
        val distances = keyPoints.map {
            distance(point, it)
        }
        return distances.indexOf(distances.min())
    }

    /**
     * Calculates the distance between two keyPoints, analyse only features to calculations
     * @param point1 first keyPoint
     * @param point2 second keyPoint
     * @return distance
     */
    private fun distance(point1: KeyPoint, point2: KeyPoint): Double {
        val sum = (0 until point1.size).map {
            //            val difference = point1.features[it] - point2.features[it]
            Math.pow((point1.features[it] - point2.features[it]).toDouble(), 2.0)
        }.sum()
        return Math.sqrt(sum)
    }
}
