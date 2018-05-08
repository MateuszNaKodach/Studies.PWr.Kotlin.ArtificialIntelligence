package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

typealias Row = Int
typealias Column = Int
typealias Chessboard = Array<IntArray>

fun Chessboard.toBoardString(): String =
        this.map { array -> array.toList().map { if (it == 1) "Q" else "_" } }
                .joinToString("\n") { it.toString().replace(",", "") }

fun Chessboard.prettyPrint() = print(this.toBoardString())
