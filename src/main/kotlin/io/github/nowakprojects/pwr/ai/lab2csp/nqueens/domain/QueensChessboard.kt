package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.CSPResult

class QueensChessboard(val chessboard: Chessboard, isSolution: Boolean) : CSPResult<Row, Column, QueenPlace>(chessboard.getCurrentQueenPlaces(), isSolution) {
}