package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.State
import java.lang.StringBuilder
import java.util.Objects.isNull
import java.util.Objects.nonNull

class Chessboard(val n: Int, queenPlaces: List<QueenPlace>) : State<Row, Column, QueenPlace>(queenPlaces) {

    fun getCurrentQueenPlaces(): List<QueenPlace> = values

    fun isQueenOnPointDiagonals(x: Row, y: Column) = getCurrentQueenPlaces()
            .any { Math.abs(it.getX() - x) == Math.abs(it.getY() - y) }

    fun isQueenInRow(y: Row) = nonNull(getQueenFromRow(y))

    fun getQueenFromRow(y: Row): QueenPlace? = getValueFor(y)

    fun isQueenInColumn(x: Column): Boolean = values.any { it.getX() == x }

    fun getQueenColumnForRow(y: Row): Column? = getValueFor(y).takeIf { it != null }?.getColumn()

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

    fun isQueenOnPlace(x: Column, y: Row): Boolean {
        val queenColumnForRow = getQueenColumnForRow(y)
        return when {
            isNull(queenColumnForRow) -> false
            else -> queenColumnForRow == x
        }
    }

    fun allQueensPlaced() = values.size == n

    override fun toString(): String {
        return StringBuilder().apply {
            (0 until n)
                    .map { y ->
                        (0 until n)
                                .map { x ->
                                    (0 until n)
                                    if (isQueenOnPlace(x, y)) "Q" else "_"
                                }
                    }.map { it.toString().replace(",", "") }.forEach { this.append(it).append("\n") }
        }.toString()
    }

    fun prettyPrint() {
        println(toString())
    }


}