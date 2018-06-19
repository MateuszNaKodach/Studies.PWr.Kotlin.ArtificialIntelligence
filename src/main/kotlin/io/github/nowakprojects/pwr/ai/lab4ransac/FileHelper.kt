import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import javax.imageio.ImageIO

/**
 * Class to transfer data with files
 * @param rootPathname the base path (of program)
 */
class FileHelper(private val rootPathname: String) {

    /**
     * Read matched point pairs form file
     * @param pathname path of file
     * @return list po point pairs
     */
    fun pointsPairs(pathname: String): List<Pair<Point, Point>> {
        val reader = FileReader("$rootPathname/$pathname")
        return Gson().fromJson(reader)
    }

    /**
     * Read picture from file
     * @param fileName file with keyPoints
     * @return picture with read keyPoints
     */
    fun keyPoints(fileName: String): Picture {
        val file = File("$rootPathname/$fileName.haraff.sift")
        val lines = file.readLines()
        val subList = lines.subList(2, lines.size)
        val keyPoints = subList.map {
            val itemsInLine = it.split(' ')
            val x = itemsInLine[0].toDouble()
            val y = itemsInLine[1].toDouble()
            val features = itemsInLine.subList(5, itemsInLine.size).map { it.toInt() }
            KeyPoint(x, y, features)
        }
        return Picture(keyPoints)
    }

    /**
     * Method to save json the list of objects to file
     * @param pathname path of file (with '.json')
     * @param toSave list to save
     */
    fun save(pathname: String, toSave: List<Any?>) {
        val fileWriter = FileWriter("$rootPathname/$pathname")
        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer = gson.newJsonWriter(fileWriter)
        writer.beginArray()
        for (element in toSave) {
            gson.toJson(element, element!!::class.java, writer)
        }
        writer.endArray()
        writer.close()
    }

    /**
     * Method to read the bitmap image
     * @param pathname file path with image
     * @return read image
     */
    fun readImage(pathname: String): BufferedImage {
        val file = File("$rootPathname/$pathname")
        return ImageIO.read(file)
    }

    /**
     * Save png image into file
     * @param image image to save
     * @param pathname path to save
     */
    fun saveImage(image: BufferedImage, pathname: String) {
        val file = File("$rootPathname/$pathname")
        ImageIO.write(image, "png", file)
    }
}
