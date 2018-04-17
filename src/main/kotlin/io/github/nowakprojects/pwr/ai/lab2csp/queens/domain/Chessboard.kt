package io.github.nowakprojects.pwr.ai.lab2csp.queens.domain

class Chessboard(
        val chessboardSize: Int,
        val fields: Array<Array<Chessman>> = Array<Array<Chessman>>
        (chessboardSize, { (0..chessboardSize).map { Chessman.NONE }.toTypedArray() })) {


    fun getColumn(xColumn: Int): Array<Chessman> = fields[xColumn]

    fun getRow(yRow: Int): Array<Chessman> = fields.map { column -> column[yRow] }.toTypedArray()

    companion object {
        fun empty(chessboardSize: Int) = Chessboard(chessboardSize)
    }
}