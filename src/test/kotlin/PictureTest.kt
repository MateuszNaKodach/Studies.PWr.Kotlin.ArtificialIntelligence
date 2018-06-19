import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PictureTest {

    @Test
    fun indexesPairs() {
        val keyPoints1 = listOf(
            KeyPoint(0.0, 0.0, listOf(1, 1, 1)),
            KeyPoint(1.0, 1.0, listOf(5, 5, 5)),
            KeyPoint(2.0, 2.0, listOf(8, 8, 8))
        )
        val keyPoints2 = listOf(
            KeyPoint(3.0, 3.0, listOf(3, 3, 3)),
            KeyPoint(4.0, 5.0, listOf(2, 2, 2)),
            KeyPoint(6.3, 1.5, listOf(6, 6, 6))
        )
        val picture1 = Picture(keyPoints1)
        val picture2 = Picture(keyPoints2)

        val indexesPairs = picture1.keyPointsPairs(picture2)

        assertThat(indexesPairs).isEqualTo(
            listOf(
                keyPoints1[0] to keyPoints2[1],
                keyPoints1[1] to keyPoints2[2]
            )
        )
    }
}
