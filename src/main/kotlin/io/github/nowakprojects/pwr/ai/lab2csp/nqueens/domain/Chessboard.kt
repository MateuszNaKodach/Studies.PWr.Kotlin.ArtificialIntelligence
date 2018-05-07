package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.State

class Chessboard(val n: Int, queenPlaces: List<QueenPlace>) : State<Row, QueenPlace>(queenPlaces) {

    fun getCurrentQueenPlaces(): List<QueenPlace> = values

    fun getQueenColumnForRow(x: Row): Column? = getValueFor(x).takeIf { it != null }?.getColumn()

    fun addQueenPlace(queenPlace: QueenPlace): Chessboard {
        if (values.size == n) {
            throw IllegalStateException("All queens are placed on the chessboard!")
        }
        val mutableList = values.toMutableList()
        mutableList.add(queenPlace)
        return Chessboard(n, mutableList)
    }

    fun removeLastQueenPlace(): Chessboard {
        return Chessboard(n, values.dropLast(1))
    }


}