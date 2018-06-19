/**
 * Class with point on map with features - the size of features is cost for all image
 * @param x 1-st dimen of point
 * @param y 2-nd dimen of point
 * @param features the list of features of point
 */
data class KeyPoint(val x: Double, val y: Double, @Transient val features: List<Int>) {

    /**
     * The size of features
     */
    @Transient
    val size = features.size
}
