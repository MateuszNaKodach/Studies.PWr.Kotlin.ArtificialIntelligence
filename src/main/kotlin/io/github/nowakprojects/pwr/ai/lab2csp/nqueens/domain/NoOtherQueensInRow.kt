package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain


class NoOtherQueensInRow : NQueenConstraint {

    override fun isSatisfiedFor(chessboard: Chessboard, queenPlace: QueenPlace): Boolean =
            !chessboard.isQueenInRow(queenPlace.getRow())
}