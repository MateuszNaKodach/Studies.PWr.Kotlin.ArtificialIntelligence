package io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator

/**
 * Object to help prepare iterators
 */
object IteratorHelper {

    /**
     * Method to return the snail road in matrix from outside to center
     */
    fun spiralPrint(side: Int): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        var i: Int
        var k = 0
        var l = 0
        var n = side
        var m = side

        /*  k - starting row index
            m - ending row index
            l - starting column index
            n - ending column index
            i - iterator
        */

        while (k < m && l < n) {
            /* Print the first row from the remaining rows */

            i = l
            while (i < n) {
                list.add(Pair(k, i))
                i++
            }
            k++

            /* Print the last column from the remaining columns */

            i = k
            while (i < m) {
                list.add(Pair(i, n - 1))
                i++
            }
            n--

            /* Print the last row from the remaining rows */
            if (k < m) {
                i = n - 1
                while (i >= l) {
                    list.add(Pair(m - 1, i))
                    i--
                }
                m--
            }

            /* Print the first column from the remaining columns */
            if (l < n) {
                i = m - 1
                while (i >= k) {
                    list.add(Pair(i, l))
                    i--
                }
                l++
            }
        }
        return list.toList()
    }
}