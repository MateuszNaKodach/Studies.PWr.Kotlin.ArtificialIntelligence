package io.github.nowakprojects.pwr.ai.lab2csp.queens

import io.github.nowakprojects.pwr.ai.lab2csp.queens.domain.Chessboard
import io.github.nowakprojects.pwr.ai.lab2csp.queens.domain.Chessman
import io.github.nowakprojects.pwr.ai.lab2csp.queens.domain.Backtracking

fun main(args: Array<String>) {
    val N = 6
    val chessboard = Chessboard.empty(N)

    val solution = Backtracking(N).findSolutionFor(0,0,1)
    solution.prettyPrint()
    /*chessboard.prettyPrint()
    println("After")
    chessboard.placeChessman(Chessman.QUEEN, 1, 2)
    chessboard.prettyPrint()
    println("Row 1");
    chessboard.getRow(2).prettyPrint()
    println("Column 2:")
    chessboard.getColumn(1).prettyPrint();
    println("Diagonal 1,2:")
    println(chessboard.areDiagonalsEmptyForPoint(1, 2));
    println(chessboard.areDiagonalsEmptyForPoint(1, 1));
    println(chessboard.isNoQueenAttackedOnPoint(1, 2))
    println(chessboard.isNoQueenAttackedOnPoint(1, 1))
    println(chessboard.isNoQueenAttackedOnPoint(3, 1))

    println(chessboard.getPointsWithQueens().toString());*/
}
/*
    println(Backtracking(N).getAllPossibleValues())(/

}*/