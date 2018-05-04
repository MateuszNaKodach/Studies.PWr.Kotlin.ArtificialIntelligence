package io.github.nowakprojects.pwr.ai.lab2csp.queens

import io.github.nowakprojects.pwr.ai.lab2csp.queens.domain.Chessboard
import io.github.nowakprojects.pwr.ai.lab2csp.queens.domain.Chessman

fun main(args: Array<String>) {
    val chessboard = Chessboard.empty(6)
    chessboard.prettyPrint()
    println("After")
    chessboard.placeChessman(Chessman.QUEEN, 1,2)
    chessboard.prettyPrint()
    println("Row 1");
    chessboard.getRow(2).prettyPrint()
    println("Column 2:")
    chessboard.getColumn(1).prettyPrint();
    println("Diagonal 1,2:")
    chessboard.areDiagonalsEmptyForPoint(1,2).prettyPrint()
}