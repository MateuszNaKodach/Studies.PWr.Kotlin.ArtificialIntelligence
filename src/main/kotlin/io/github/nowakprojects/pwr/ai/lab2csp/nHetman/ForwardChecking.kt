package io.github.nowakprojects.pwr.ai.lab2csp.nHetman

/**
 * Class to find solutions in nQueens using forward checking
 * @param chessboard the chessboard where we find solutions
 */
class ForwardChecking(private val chessboard: Chessboard) {

    /**
     * Set with solutions, I didn't wrote deep hashCode() and equal(...) methods, so the same solutions can will repeat.
     * It is good to find bugs
     */
    private val solutionSet = HashSet<Chessboard>()

    /**
     * The set of domain for rows - calculated only from vertical queens
     */
    private val ySet = mutableSetOf<Int>()

    /**
     * Time for finding start - it is used in code analyse
     */
    private var timeStart = 0L

    /**
     * Variable used to show time of first solution
     */
    private var foundFirst = false

    /**
     * This is the count of elementary method for search - it's used to analyse
     */
    private var findRun = 0

    /**
     * The table of row/column iterator from center
     */
    private val permutationMatrixCenter = IntArray(chessboard.size, {
        val half = (chessboard.size - 1) / 2
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
     * The table of row/column iterator from center
     */
    private val permutationMatrixClassic = (0 until chessboard.size).toList().toIntArray()

    /**
     * First dimen permutation matrix
     */
    // you can change this
    private val firstDimenPermutationMatrix = permutationMatrixClassic

    /**
     * Second dimen permutation matrix
     */
    // you can change this
    private val secondDimenPermutationMatrix = permutationMatrixClassic


    /**
     * Method finds the solution
     * @return set of solutions
     */
    fun findSolution(): Set<Chessboard> {
        chessboard.initialize()
        ySet.clear()
        solutionSet.clear()
        findRun = 0
        foundFirst = false
        timeStart = System.nanoTime()
        findInX(0)
        println("forward: " + (System.nanoTime() - timeStart) + "ns")
        println("find run: $findRun")
        return solutionSet
    }

    /**
     * Find the solution - elementary method
     * @param x index of row where we want insert queen
     */
    private fun findInX(x: Int) {
        findRun++
        if (x == chessboard.size) {
            solutionSet.add(chessboard.clone())
            if (!foundFirst) {
                println("forward first: " + (System.nanoTime() - timeStart) + "ns")
                foundFirst = true
            }
        } else {
            val xP = firstDimenPermutationMatrix[x]
            secondDimenPermutationMatrix.forEach {
                if (!ySet.contains(it) && chessboard.isFieldDiagonalOk(xP, it)) {
                    chessboard.field[xP][it] = true
                    ySet.add(it)
                    findInX(x + 1)
                    chessboard.field[xP][it] = false
                    ySet.remove(it)
                }
            }
        }
    }
}