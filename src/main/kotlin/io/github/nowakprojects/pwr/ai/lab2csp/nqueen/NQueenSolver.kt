package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

class NQueenSolver(val n: Int) {

    /**
     * INITIAL SETTINGS
     */
    private var chessboard: Chessboard = ChessboardBuilder.empty(n)
    private var rowsPermutation: List<Int> = emptyList()
    private var columnsPermutation: List<Int> = emptyList()

    /*
    STATISTICS -------------------------------------------------------------------------------------------------------
     */
    val solutions = mutableListOf<Chessboard>()
    var startTime: Long = 0
    var durationNano: Long = 0
    var firstSolutionFound = false
    var firstSolutionFoundInNano: Long = 0
    var findMethodInvocations: Long = 0

    fun solveWith(
            algorithm: Algorithm,
            rowPermutation: PERMUTATION = PERMUTATION.ORDERED,
            columnPermutation: PERMUTATION = PERMUTATION.ORDERED): Statistics {
        clearStatistics()
        setupRowAndColumnPermutations(rowPermutation, columnPermutation)
        startTime = System.nanoTime()
        val solutions = when (algorithm) {
            Algorithm.BACK_TRACKING -> solveWithBackTracking()
            Algorithm.FORWARD_CHECKING -> solveWithForwardChecking()
        }
        durationNano = System.nanoTime() - startTime
        return Statistics(n, solutions, durationNano, firstSolutionFoundInNano, findMethodInvocations)
    }

    private fun setupRowAndColumnPermutations(rowPermutation: PERMUTATION, columnPermutation: PERMUTATION) {
        rowsPermutation = when (rowPermutation) {
            PERMUTATION.ORDERED -> (0 until n).toList()
            PERMUTATION.FROM_MIDDLE -> (0 until n).permutationFromMiddle()
        }
        columnsPermutation = when (columnPermutation) {
            PERMUTATION.ORDERED -> (0 until n).toList()
            PERMUTATION.FROM_MIDDLE -> (0 until n).permutationFromMiddle()
        }
    }

    /**
     * BACKTRACKING ------------------------------------------------------------------------------------------------
     */
    private fun solveWithBackTracking(): List<Chessboard> {
        solveNQueenProblemWithBacktracking(0,0)
        return solutions
    }

    private fun solveNQueenProblemWithBacktracking(placedQueens: Int, currentField: Int) {
        findMethodInvocations++
        if (allQueensPlaced(placedQueens)) {
            storeSolution()
        }
        (0 until n * n).forEach { field ->
            val column = field / n
            val row = field % n
            if (isSafePosition(chessboard, row, column)) {
                chessboard[row][column] = 1
                solveNQueenProblemWithBacktracking(placedQueens + 1, currentField + 1)
                chessboard[row][column] = 0
            }
        }
    }

    val selectedRows = mutableSetOf<Row>()

    /**
     * FORWARDCHECKING ------------------------------------------------------------------------------------------------
     */
    private fun solveWithForwardChecking(): List<Chessboard> {
        solutions.clear()
        solveNQueenProblemWithForwardChecking(0)
        return solutions
    }

    private fun solveNQueenProblemWithForwardChecking(column: Column) {
        findMethodInvocations++
        if (allQueensPlaced(column)) {
            storeSolution()
        }
        rowsPermutation.forEach { row ->
            if (!selectedRows.contains(row) && !isQueenOnDiagonals(chessboard, row, column)) {
                chessboard[row][column] = 1
                selectedRows.add(row)
                solveNQueenProblemWithForwardChecking(column + 1)
                chessboard[row][column] = 0
                selectedRows.remove(row)
            }
        }
    }

    /**
     * COMMON -----------------------------------------------------------------------------------------------
     */

    private fun storeSolution() {
        if (!firstSolutionFound) {
            firstSolutionFoundInNano = System.nanoTime() - startTime
            firstSolutionFound = true
        }
        val solutionSnapshot = chessboard.map { it.copyOf() }.toTypedArray()
        solutions.add(solutionSnapshot)
    }

    private fun allQueensPlaced(currentColumn: Column) = currentColumn >= n

    private fun isSafePosition(chessboard: Chessboard, row: Row, column: Column) =
            !(isQueenInRow(chessboard, row) || isQueenInColumn(chessboard, column) || isQueenOnDiagonals(chessboard, row, column))

    private fun isQueenInRow(chessboard: Chessboard, row: Row): Boolean = chessboard.isQueenInRow(row)

    private fun isQueenInColumn(chessboard: Chessboard, column: Column) = chessboard.isQueenInColumn(column)

    private fun isQueenOnDiagonals(chessboard: Chessboard, row: Row, column: Column) = chessboard.isQueenOnDiagonals(row, column)

    fun prettyPrintCurrentChessboard() {
        prettyPrintChessboard(chessboard)
    }

    fun prettyPrintChessboard(chessboard: Chessboard) {
        chessboard.prettyPrint()
        println()
        println()
    }

    private fun clearStatistics() {
        chessboard = ChessboardBuilder.empty(n)
        solutions.clear()
        selectedRows.clear()
        durationNano = 0
        startTime = 0
        firstSolutionFoundInNano = 0
        firstSolutionFound = false
        findMethodInvocations = 0
    }

    enum class Algorithm {
        FORWARD_CHECKING,
        BACK_TRACKING;
    }

    enum class PERMUTATION {
        ORDERED,
        FROM_MIDDLE;
    }
}

data class Statistics(
        val nQueens: Int,
        val solutions: List<Chessboard>,
        val durationNano: Long,
        val firstSolutionFoundInNano: Long,
        val findMethodInvocations: Long) {

    override fun toString(): String {
        return "Liczba królowych (N) \t| Rozwiązań: \t| Czas wyszukania 1. rozwiązania: \t| Czas wyszukania wszystkich rozwiązań: \t| Liczba wywołań metody rekurencyjnej: \n" +
                "${nQueens} \t| ${solutions.size} \t| $durationNano \t| ${firstSolutionFoundInNano} \t| ${findMethodInvocations}"
    }

    fun getFirstSolution() = solutions.first()

    fun printFirstSolution() = getFirstSolution().prettyPrint()
}

