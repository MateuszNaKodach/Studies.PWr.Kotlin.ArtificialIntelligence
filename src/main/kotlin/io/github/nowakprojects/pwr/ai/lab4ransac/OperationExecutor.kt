import transform.Transform
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

/**
 * Class to make all actions defined in exercise
 * @param rootPathname root pathname of data
 */
class OperationExecutor(rootPathname: String) {

    /**
     * The file helper to work with files
     */
    private val fileHelper = FileHelper(rootPathname)

    /**
     * Method to preapre and save the consistent pairs
     * @param pairsFileName the file with the input pairs
     * @param destFileName file name to save the result (the .json will be added)
     * @param neighborhoodSize the neighbourhood size
     */
    fun saveConsistentPairs(pairsFileName: String, destFileName: String, neighborhoodSize: Int, threshold: Double) {
        val pairs = fileHelper.pointsPairs("$pairsFileName.json")
        val consistentPairs = PairsProcessor.consistentPairs(pairs, neighborhoodSize, threshold)
        fileHelper.save("$destFileName.json", consistentPairs)
    }

    /**
     * Method prepare the keyPoints paris in image1 and image2
     * @param fileName1 image file 1 path
     * @param fileName2 image file 2 path
     * @param resultFileName result file name (without .json)
     */
    fun savePairs(fileName1: String, fileName2: String, resultFileName: String) {
        val picture1 = fileHelper.keyPoints(fileName1)
        val picture2 = fileHelper.keyPoints(fileName2)
        fileHelper.save("$resultFileName.json", picture1.keyPointsPairs(picture2))
    }

    /**
     * Draw the lines on double pictures
     * @param image1Pathname path of first image
     * @param image2Pathname path of second image
     * @param pairsFileName path of pairs file json
     * @return method save the {pairsFileName}.png with drove lines
     */
    fun drawLines(image1Pathname: String, image2Pathname: String, pairsFileName: String) {
        val firstImage = fileHelper.readImage(image1Pathname)
        val secondImage = fileHelper.readImage(image2Pathname)
        val newImage = BufferedImage(firstImage.width, firstImage.height + secondImage.height,
                BufferedImage.TYPE_INT_ARGB)
        with(newImage.graphics) {
            drawImage(firstImage, 0, 0, null)
            drawImage(secondImage, 0, firstImage.height, null)
            color = Color.RED
            val pairs = fileHelper.pointsPairs("$pairsFileName.json")
            println("Pairs count: ${pairs.size}")
            pairs.forEach {
                val firstPoint = it.first
                val secondPoint = it.second
                drawLine(
                        firstPoint.x.roundToInt(),
                        firstPoint.y.roundToInt(),
                        secondPoint.x.roundToInt(),
                        firstImage.height + secondPoint.y.roundToInt()
                )
            }
        }
        fileHelper.saveImage(newImage, "$pairsFileName.png")
    }

    /**
     * Method to get filtered points by ransac
     * @param pairsFileName file name of point pairs to filter
     * @param maxError max permissible error
     * @param iterationsCount count of ransac iteration
     * @param destPath the path to save the filtered result
     * @param transform the ransac transformation
     */
    fun useRansac(pairsFileName: String, maxError: Int, iterationsCount: Int, destPath: String, transform: Transform) {
        val pointsPairs = fileHelper.pointsPairs("$pairsFileName.json")
        val ransac = Ransac(transform)
        val newPairs = ransac.filterWithRansac(pointsPairs, maxError, iterationsCount)
        fileHelper.save("$destPath.json", newPairs)
    }
}
