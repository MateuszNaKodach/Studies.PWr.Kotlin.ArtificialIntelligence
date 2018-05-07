package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Constraint

class NoOtherQueensInRow : Constraint<Chessboard, QueenPlace> {

    override fun isSatisfiedFor(chessboard: Chessboard, queenPlace: QueenPlace): Boolean =
            chessboard.getQueenColumnForRow(queenPlace.getRow()) == null
}