package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain


class NoOtherQueensOnDiagonals : NQueenConstraint {

    override fun isSatisfiedFor(chessboard: Chessboard, queenPlace: QueenPlace): Boolean {
        val queenX = queenPlace.getX()
        val queenY = queenPlace.getY()
        return !chessboard.isQueenOnPointDiagonals(queenX, queenY)
    }
}