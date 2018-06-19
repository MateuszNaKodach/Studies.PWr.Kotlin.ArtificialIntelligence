import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class OperationExecutorTest {

    private val rootPathname = "src/test/resources/OperationExecutor"
    private val executor = OperationExecutor(rootPathname)

    @Test
    fun savePairs() {
        val resultFilename = "auto-deleting"
        val resultFile = File("$rootPathname/$resultFilename.json")
        val expectedJson = File("$rootPathname/expected-pairs.json").readText()

        executor.savePairs("1.png", "2.png", resultFilename)
        val json = resultFile.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        resultFile.delete()
    }

    @Test
    fun saveConsistentPairs() {
        val resultFileName = "auto-deleting"
        val resultFile = File("$rootPathname/$resultFileName.json")
        val expectedJson = File("$rootPathname/expected-consistent-pairs.json").readText()

        executor.saveConsistentPairs("all-pairs", resultFileName, 1, 0.9)
        val json = resultFile.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        resultFile.delete()
    }

    @Test
    fun drawLines() {
        val pairsFileName = "pairs"
        val resultFile = File("$rootPathname/$pairsFileName.png")
        val expectedContent = File("$rootPathname/expected.png").readBytes()

        executor.drawLines("1.png", "2.png", pairsFileName)
        val content = resultFile.readBytes()

        assertThat(content).isEqualTo(expectedContent)

        resultFile.delete()
    }
}
