import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class FileHelperTest {

    private val rootPathname = "src/test/resources/FileHelper"
    private val io = FileHelper(rootPathname)

    @Test
    fun keyPoints() {
        val expectedPicture = Picture(
            listOf(
                KeyPoint(0.0, 0.0, listOf(1, 2)),
                KeyPoint(0.0, 0.0, listOf(3, 4))
            )
        )

        val picture = io.keyPoints("test1.png")

        assertThat(picture).isEqualTo(expectedPicture)
    }

    @Test
    fun save() {
        val pathname = "auto-deleting.json"
        val content = listOf("a", "b", "c")
        val file = File("$rootPathname/$pathname")
        val expectedJson = File("$rootPathname/expected.json").readText()

        io.save(pathname, content)
        val json = file.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        file.delete()
    }

    @Test
    fun pointsPairs() {
        val expectedPairs = listOf(
            Point(1.1, 1.1) to Point(2.2, 2.2),
            Point(3.3, 3.3) to Point(4.4, 4.4)
        )

        val pairs = io.pointsPairs("pairs.json")

        assertThat(pairs).isEqualTo(expectedPairs)
    }
}
