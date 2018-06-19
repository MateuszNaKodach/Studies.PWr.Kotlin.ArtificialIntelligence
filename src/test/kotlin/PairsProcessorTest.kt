import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PairsProcessorTest {

    @Test
    fun consistentPairs() {
        val input = listOf(
            Pair(Point(1.1, 1.1), Point(3.3, 3.3)),
            Pair(Point(2.2, 2.2), Point(6.6, 6.6)),
            Pair(Point(4.4, 4.4), Point(5.5, 5.5))
        )

        val result = PairsProcessor.consistentPairs(input, 1, 0.9)

        assertThat(result).isEqualTo(
            listOf(input[2])
        )
    }
}
