package io.github.nowakprojects.pwr.ai.lab2csp.nHetman

/**
 * Class to find solutions in nQueens using backtracking
 * @param chessboard the chessboard where we find solutions
 */
class BacktrackMethod(private val chessboard: Chessboard) {

    /**
     * Set with solutions, I didn't wrote deep hashCode() and equal(...) methods, so the same solutions can will repeat.
     * It is good to find bugs
     */
    private val solutionSet = HashSet<Chessboard>()

    /**
     * Time for finding start - it is used in code analyse
     */
    private var startInNano: Long = 0

    /**
     * Variable used to show time of first solution
     */
    private var foundFirst = false

    /**
     * Count of all fields to fill
     */
    private val fieldsCount = chessboard.size * chessboard.size

    /**
     * This is the count of elementary method for search - it's used to analyse
     */
    private var find = 0

    /**
     * The table of row/column iterator from center
     */
    private val permutationMatrix = IntArray(chessboard.size, {
        val half = (chessboard.size-1) / 2
        var toAdd = 0
        var temp = it
        while (temp > 0) {
            temp -= 2
            toAdd++
        }
        return@IntArray if (it % 2 == 1)
            half + toAdd else half - toAdd
    })

    /**
     * Method finds the solution
     * @return set of solutions
     */
    fun findSolution(): Set<Chessboard> {
        chessboard.initialize()
        solutionSet.clear()
        find = 0
        foundFirst = false
        startInNano = System.nanoTime()
        (0 until fieldsCount).map {
            // you can change this for other heuristic, remember of change in second place
            val x = it / chessboard.size
            val y = it % chessboard.size
//            val x = permutationMatrix[it / chessboard.size]
//            val y = permutationMatrix[it % chessboard.size]
            if (chessboard.isFieldOk(x, y)) {
                chessboard.field[x][y] = true
                find(1, it)
                chessboard.field[x][y] = false
            }
        }

        println("backtrack: " + (System.nanoTime() - startInNano) + "ns")
        println("find run: $find")
        return solutionSet
    }

    /**
     * Find the solution
     * @param placed count of placed queens
     * @param currentField current filed witch we consider
     */
    private fun find(placed: Int, currentField: Int) {
        find++
        if (placed == chessboard.size)
        {
            solutionSet.add(chessboard.clone())
            if (!foundFirst) {
                foundFirst = true
                println("backtrack first: " + (System.nanoTime() - startInNano) + "ns")

            }
        }
        else {
            (currentField + 1 until fieldsCount).forEach {
                // you can change this for other heuristic, remember of change in second place
//                val x1 = permutationMatrix[it / chessboard.size]
//                val y1 = permutationMatrix[it % chessboard.size]
                val x1 = it / chessboard.size
                val y1 = it % chessboard.size

                if (chessboard.isFieldOk(x1, y1)) {
                    chessboard.field[x1][y1] = true
                    find(placed + 1, it)
                    chessboard.field[x1][y1] = false
                }
            }
        }
    }

}