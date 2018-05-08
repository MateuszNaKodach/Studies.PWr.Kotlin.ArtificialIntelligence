package io.github.nowakprojects.pwr.ai.lab2csp.nqueen


class NQueenSolver(val n: Int) {

    private val chessboard: Chessboard = ChessboardBuilder.empty(n)

    /*
    STATISTICS -------------------------------------------------------------------------------------------------------
     */
    val solutions = mutableSetOf<Chessboard>()
    var durationNano: Long = 0

    fun solveWith(algorithm: Algorithm): Set<Chessboard> {
        clearStatistics()
        val startTime = System.nanoTime()
        val solutions = when (algorithm) {
            Algorithm.BACK_TRACKING -> solveWithBackTracking()
            Algorithm.FORWARD_CHECKING -> solveWithForwardChecking()
        }
        durationNano = System.nanoTime() - startTime
        return solutions
    }

    /**
     * BACKTRACKING ------------------------------------------------------------------------------------------------
     */
    private fun solveWithBackTracking(): Set<Chessboard> {
        solveNQueenProblemWithBacktracking(0)
        return solutions
    }

    private fun solveNQueenProblemWithBacktracking(column: Column) {
        if (allQueensPlaced(column)) {
            val solutionSnapshot = chessboard.map { it.copyOf() }.toTypedArray()
            solutions.add(solutionSnapshot)
        }
        (0 until chessboard.size).forEach { row ->
            if (isSafePosition(chessboard, row, column)) {
                chessboard[row][column] = 1
                solveNQueenProblemWithBacktracking(column + 1)
                chessboard[row][column] = 0
            }
        }
    }

    val selectedRows = mutableSetOf<Row>()

    /**
     * FORWARDCHECKING ------------------------------------------------------------------------------------------------
     */
    fun solveWithForwardChecking(): Set<Chessboard> {
        solutions.clear()
        solveNQueenProblemWithForwardChecking(0)
        return solutions
    }

    private fun solveNQueenProblemWithForwardChecking(column: Column) {
        if (allQueensPlaced(column)) {
            val solutionSnapshot = chessboard.map { it.copyOf() }.toTypedArray()
            solutions.add(solutionSnapshot)
        }
        (0 until chessboard.size).forEach { row ->
            if (!selectedRows.contains(row) && !isQueenOnDiagonals(chessboard, row, column)) {
                chessboard[row][column] = 1
                selectedRows.add(row)
                solveNQueenProblemWithBacktracking(column + 1)
                chessboard[row][column] = 0
                selectedRows.remove(row)
            }
        }
    }


    /**
     * COMMON -----------------------------------------------------------------------------------------------
     */
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
    }

    private fun clearStatistics() {
        solutions.clear()
        selectedRows.clear()
        durationNano = 0
    }

    enum class Algorithm {
        FORWARD_CHECKING,
        BACK_TRACKING;
    }
}

