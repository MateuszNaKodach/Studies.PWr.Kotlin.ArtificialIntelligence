import org.assertj.core.api.Assertions.assertThat
import org.ejml.simple.SimpleMatrix
import org.junit.jupiter.api.Test

internal class RansacTest {

    @Test
    fun bestModel() {
        val input = listOf(
            Pair(Point(64.4551, 196.661), Point(195.946, 49.8971)),
            Pair(Point(143.421, 38.6334), Point(85.9343, 203.734)),
            Pair(Point(379.754, 325.584), Point(745.472, 375.924))
        )
        val expectedModel = SimpleMatrix(
            3, 3, true,
            doubleArrayOf(
                1.211, 1.301, -137.993,
                1.189, -0.379, 47.844,
                0.000, 0.000, 1.000
            )
        )

        val bestModel = Ransac().bestModel(input, 30, 1)
        val sameAsExpected = bestModel.isIdentical(expectedModel, 0.001)

        assertThat(sameAsExpected).isTrue()
    }
}
