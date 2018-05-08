package io.github.nowakprojects.pwr.ai.lab2csp.latianSquare

/**
 * Class to find solutions in latian square using forward checking
 * @param square the square where we find solutions
 */
class ForwardMethod(private val square: Square) {

    /**
     * Set with solutions, I didn't wrote deep hashCode() and equal(...) methods, so the same solutions can will repeat
     * It is good to find bugs
     */
    private val solutionSet = HashSet<Square>()

    /**
     * Count of all latianSquare size
     */
    private val fieldsCount = square.size * square.size

    /**
     * The range used in algorithm - used for optimization
     */
    private val sideRange = (0 until square.size)

    /**
     * Time for finding start - it is used in code analyse
     */
    private var time = 0L

    /**
     * Variable used to show time of first solution
     */
    private var foundFirst = false

    /**
     * This is the count of elementary method for search - it's used to analyse
     */
    private var findRun = 0

    /**
     * This is the container of domain in square, the domain is always current
     */
    private val domainSquare = Array(square.size, { Array(square.size, { (1..square.size).toMutableSet() }) })

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
        time = System.nanoTime()
        (0 until square.size).forEach { removeValueFromNeighbourhoodDomain(it, 0, square.field[it][0]) }
        if (square.size > 1)
            find(square.size)
//            find(-)
        else if (square.size == 1)
            solutionSet.add(square)
        println("forward: " + (System.nanoTime() - time) + "ns")
        println("find run: $findRun")
        return solutionSet
    }

    /**
     * This is the elementary searching method
     * @param position the absolute number of position where method may search the solution
     */
    private fun find(position: Int) {
        findRun++

        // HERE WE CAN CHANGE THE HEURISTIC ON CHOOSE NEXT FIELD
//        val w = position % square.size
//        val h = position / square.size
        val pair = findFieldWithSmallestDomain(position)
        val w = pair.first
        val h = pair.second


        domainSquare[w][h].forEach {
            square.field[w][h] = it

            val result =
                    removeValueFromNeighbourhoodDomain(w, h, it)

            if (result.first) {
                if (position + 1 != fieldsCount)
                    find(position + 1)
                else {
                    solutionSet.add(square.clone())
                    if (!foundFirst) {
                        println("forward first: " + (System.nanoTime() - time) + "ns")
                        foundFirst = true
                    }
                }
            }

            sideRange.forEach { ind ->
                if (result.second[ind])
                    domainSquare[w][ind].add(it)
                if (result.third[ind])
                    domainSquare[ind][h].add(it)
            }
        }
        square.field[w][h] = 0
    }

    /**
     * Method to find filed with the smallest domain
     * Here is used the optimization deduced from used forward checking method, we insert the row in sequence, so search
     * in all square haven't got sens
     * @param nb number of searching filed
     * @return pair od first and second dimension of square
     */
    private fun findFieldWithSmallestDomain(nb: Int): Pair<Int, Int> {

        if (nb % square.size == 0)
            return Pair(nb % square.size, nb / square.size)

        var minSize = Int.MAX_VALUE
        var w = 0

        val h = nb / square.size
        sideRange.forEach { t ->
            if (square.field[t][h] == 0) {
                if (domainSquare[t][h].size < minSize) {
                    minSize = domainSquare[t][h].size
                    w = t
                }
            }
        }
        return Pair(w, h)
    }

    /**
     * This method updates the domains of fields in square, it works correctly when this method is using with all
     * updates of square
     * @param w first dimension of updated square
     * @param h second dimension of updated square
     * @param value updated value
     * @return Triple(is empty domain with empty filed, matrix of updates [w,?], matrix of updates [?,h])
     */
    private fun removeValueFromNeighbourhoodDomain(w: Int, h: Int, value: Int)
            : Triple<Boolean, BooleanArray, BooleanArray> {
        var returnVal = true

        val wList = BooleanArray(square.size)
        val hList = BooleanArray(square.size)

        (0 until square.size).forEach {
            if (square.field[w][it] == 0) {
                wList[it] = domainSquare[w][it].remove(value)
                if (domainSquare[w][it].isEmpty())
                    returnVal = false
            }

            if (square.field[it][h] == 0) {
                hList[it] = (domainSquare[it][h].remove(value))
                if (domainSquare[it][h].isEmpty())
                    returnVal = false
            }
        }

        return Triple(returnVal, wList, hList)
    }

}