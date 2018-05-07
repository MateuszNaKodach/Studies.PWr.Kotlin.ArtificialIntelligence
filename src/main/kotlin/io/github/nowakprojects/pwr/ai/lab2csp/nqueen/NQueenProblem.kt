package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

import java.lang.StringBuilder
import kotlin.math.abs

typealias Chessboard = Array<IntArray>
typealias Row = Int
typealias Column = Int

class NQueenProblem(val n: Int) {

    private val chessboard: Chessboard = Array(n, { IntArray(n, { 0 }) })
    val solutions = mutableSetOf<Chessboard>()


    fun solveWithBackTracking() = solveNQueenProblem(0)

    private fun solveNQueenProblem(column: Column) {
        if (allQueensPlaced(column, n)) {
            val solutionSnapshot = chessboard.map { it.copyOf() }.toTypedArray()
            solutions.add(solutionSnapshot)
        }
        (0 until chessboard.size).forEach { row ->
            if (isSafePosition(chessboard, row, column)) {
                chessboard[row][column] = 1
                solveNQueenProblem(column + 1)
                chessboard[row][column] = 0
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
            }

    fun prettyPrintChessboard() {
        print(toString(chessboard))
    }

    fun prettyPrintChessboard(chessboard: Chessboard) {
        print(toString(chessboard))
    }

}