package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

import java.lang.StringBuilder
import kotlin.math.abs


class NQueenProblem(val n: Int) {

    private val chessboard: Chessboard = Array(n, { IntArray(n, { 0 }) })
    val solutions = mutableSetOf<Chessboard>()


    fun solveWithBackTracking() {
        solutions.clear()
        solveNQueenProblemWithBacktracking(0)
    }

    private fun solveNQueenProblemWithBacktracking(column: Column) {
        if (allQueensPlaced(column, n)) {
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

    fun solveWithForwardChecking() {
        solutions.clear()
        solveNQueenProblemWithForwardChecking(0)
    }

    private fun solveNQueenProblemWithForwardChecking(column: Column) {
        if (allQueensPlaced(column, n)) {
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

    private fun allQueensPlaced(currentColumn: Column, nQueens: Int) = currentColumn >= n


    private fun isSafePosition(chessboard: Chessboard, row: Row, column: Column) =
            !(isQueenInRow(chessboard, row) || isQueenInColumn(chessboard, column) || isQueenOnDiagonals(chessboard, row, column))


    private fun isQueenInRow(chessboard: Chessboard, row: Row): Boolean = chessboard[row].any { it == 1 }

    private fun isQueenInColumn(chessboard: Chessboard, column: Column) = (0 until chessboard.size).any { row -> chessboard[row][column] == 1 }

    private fun isQueenOnDiagonals(chessboard: Chessboard, row: Row, column: Column): Boolean {
        for (y in 0 until chessboard.size) {
            for (x in 0 until chessboard[y].size) {
                if (abs(x - column) == abs(y - row) && chessboard[y][x] == 1) {
                    return true
                }
            }
        }
        return false
    }


    private fun toString(chessboard: Chessboard) =
            StringBuilder().apply {
                chessboard
                        .map { array -> array.toList().map { if (it == 1) "Q" else "_" } }
                        .map { it.toString().replace(",", "") }.forEach { this.append(it).append("\n") }
                        .toString()
            }.toString()

    fun prettyPrintChessboard() {
        prettyPrintChessboard(chessboard)
    }

    fun prettyPrintChessboard(chessboard: Chessboard) {
        chessboard.prettyPrint()
        println()
    }

}