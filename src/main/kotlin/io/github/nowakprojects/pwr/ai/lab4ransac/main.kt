import heuristics.*
import transform.*

private val names = listOf("kaczka", "kubek", "muza", "mysz", "radek", "castle", "book", "nutella", "architecture")

private val pairsFileName = names[6]

private const val neighborhoodSize = 6
private const val threshold = 0.5

private const val maxError = 10
private const val iterationsCount = 100000

private const val r = 4.0
private const val R = 700.0

//private val heuristics: Heuristics = SimpleSquareHeuristic(r, R)
private val heuristics: Heuristics = VerySimpleHeuristics

private val transform = AffineTransform(heuristics)
//private val transform = PerspectiveTransform(heuristics)

private val pairsPath = "all/$pairsFileName"
private val image1 = "${pairsFileName}1.png"
private val image2 = "${pairsFileName}2.png"
private val consistentPairsPath = "consistent/$pairsFileName,n=$neighborhoodSize,t=$threshold"
private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
    measure {
//        executor.savePairs("haraff/$image1", "haraff/$image2", pairsPath)
//        drawPairs(pairsPath)
//        makeConsistentPairsImage()
        makeRansacImage()
    }
}

private fun makeConsistentPairsImage() {
    executor.saveConsistentPairs(pairsPath, consistentPairsPath, neighborhoodSize, threshold)
    drawPairs(consistentPairsPath)
}

fun getHeuristicName(heuristics: Heuristics): String {
    return when (heuristics) {
        is SimpleSquareHeuristic -> "square"
        is SimpleCanberraHeuristic -> "canberra"
        else -> "nonHeuristic"
    }
}

private fun makeRansacImage() {
    val pathSuffix = getHeuristicName(heuristics) + if (heuristics is SimpleHeuristics) ",r=$r,R=$R" else ""
    val destPath = "ransac/${pairsFileName}_m=$maxError,i=$iterationsCount$pathSuffix"
    executor.useRansac(pairsPath, maxError, iterationsCount, destPath, transform)
    drawPairs(destPath)
}

private fun drawPairs(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", pairsPath)
}
