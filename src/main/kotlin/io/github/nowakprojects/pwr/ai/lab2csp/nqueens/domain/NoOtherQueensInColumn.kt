package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class NoOtherQueensInColumn : NQueenConstraint {

    override fun isSatisfiedFor(chessboard: Chessboard, queenPlace: QueenPlace): Boolean =
            !chessboard.isQueenInColumn(queenPlace.getColumn())
}