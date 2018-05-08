package io.github.nowakprojects.pwr.ai.lab2csp.latianSquare

/**
 * Class to find solutions in latian square using backtracking
 * @param square the square where we find solutions
 */
class BacktrackMethod(private val square: Square) {

    /**
     * Set with solutions, I didn't wrote deep hashCode() and equal(...) methods, so the same solutions can will repeat
     * It is good to find bugs
     */
    private val solutionSet = HashSet<Square>()

    /**
     * The domain range
     */
    private val domain = (1..square.size)

    /**
     * This is the count of elementary method for search - it's used to analyse
     */
    var findRun = 0

    /**
     * Time for finding start - it is used in code analyse
     */
    var timeStart = 0L

    /**
     * Variable used to show time of first solution
     */
    var foundFirst = false

    /**
     * Count of all latianSquare size
     */
    private val fieldsCount = square.size * square.size

    /**
     * The public function to calculate the set of solutions
     * @return set of solutions
     */
    fun findSolution(): Set<Square> {
        // IF YOU WANT CHANGE THE PERMUTATION HEURISTIC CHANGE COMMENTED LINES
        square.initialize()
//        square.initialize(false)
        solutionSet.clear()
        findRun = 0
        foundFirst = false
        timeStart = System.nanoTime()
        if (square.size > 1)
            find(square.size)
//            find(0)
        else if (square.size == 1)
            solutionSet.add(square)
        println("backtrack: " + (System.nanoTime() - timeStart) + "ns")
        println("find run: $findRun")
        return solutionSet
    }

    /**
     * This is the elementary searching method
     * @param position the absolute number of position where method may search the solution
     */
    private fun find(position: Int) {
        findRun++
        val w = position % square.size
        val h = position / square.size
        domain.forEach {
            if (square.checkField(w, h, it)) {
                square.field[w][h] = it
                if (position + 1 != fieldsCount)
                    find(position + 1)
                else
                {
                    solutionSet.add(square.clone())
                    if (!foundFirst) {
                        foundFirst = true
                        println("backtrack first: " + (System.nanoTime() - timeStart) + "ns")
                    }
                }
            }
        }
        square.field[w][h] = 0
    }

}