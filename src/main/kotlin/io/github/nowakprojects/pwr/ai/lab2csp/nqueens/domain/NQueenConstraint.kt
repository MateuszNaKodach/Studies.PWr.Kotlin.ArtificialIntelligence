package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Constraint

interface NQueenConstraint : Constraint<Row, Column, QueenPlace, Chessboard> {
}