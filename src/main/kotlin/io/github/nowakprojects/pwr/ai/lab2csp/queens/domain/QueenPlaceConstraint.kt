package io.github.nowakprojects.pwr.ai.lab2csp.queens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Constraint

class QueenPlaceConstraint(private val chessboard: Chessboard) : Constraint<Point> {

    override fun isSatisfiedFor(t: Point): Boolean = chessboard.isNoQueenAttackedOnPoint(t.x, t.y)
}
