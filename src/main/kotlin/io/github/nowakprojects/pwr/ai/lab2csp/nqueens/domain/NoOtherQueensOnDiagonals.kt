package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Constraint
import java.lang.Math.abs

class NoOtherQueensOnDiagonals : Constraint<Chessboard, QueenPlace> {

    override fun isSatisfiedFor(chessboard: Chessboard, queenPlace: QueenPlace): Boolean {
        val queenX = queenPlace.getX()
        val queenY = queenPlace.getY()
        return chessboard.getCurrentQueenPlaces()
                .none { abs(it.getX() - queenX) == abs(it.getY() - queenY) }
    }
}