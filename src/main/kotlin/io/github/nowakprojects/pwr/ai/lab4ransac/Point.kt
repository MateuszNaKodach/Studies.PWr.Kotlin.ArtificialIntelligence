import java.lang.Math.abs

/**
 * Class of point in picture
 * @param x dimension 1
 * @param y dimension 2
 */
data class Point(val x: Double, val y: Double) {

    /**
     * Method returns the square distance between other point
     * @param point other point
     * @return distance
     */
    fun distanceSquare(point: Point) = Math.pow(x - point.x, 2.0) + Math.pow(y - point.y, 2.0)

    fun distanceCanberra(point: Point) = abs(x - point.x) / (abs(x) + abs(point.x)) +
            abs(y - point.y) / (abs(y) + abs(point.y))
}
