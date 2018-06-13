package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

import kotlin.math.abs

typealias Row = Int
typealias Column = Int
typealias Chessboard = Array<IntArray>

fun Chessboard.toBoardString(): String =
        this.map { array -> array.toList().map { if (it == 1) "Q" else "_" } }
                .joinToString("\n") { it.toString().replace(",", "") }

fun Chessboard.prettyPrint() = print(this.toBoardString())

fun Chessboard.isQueenInRow(row: Row) = this[row].any { it == 1 }

fun Chessboard.isQueenInColumn(column: Column) = (0 until this.size).any { row -> this[row][column] == 1 }

fun Chessboard.isQueenOnDiagonals(row: Row, column: Column): Boolean {
    for (y in 0 until this.size) {
        for (x in 0 until this[y].size) {
            if (abs(x - column) == abs(y - row) && this[y][x] == 1) {
                return true
            }
        }
    }
    return false
}

fun Chessboard.isSafePosition(row: Row, column: Column): Boolean =
        !(isQueenInRow(row) || isQueenInColumn(column) || isQueenOnDiagonals(row, column))

object ChessboardBuilder {
    fun empty(n: Int) = Chessboard(n, { IntArray(n, { 0 }) })
}



