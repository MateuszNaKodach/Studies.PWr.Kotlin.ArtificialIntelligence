package io.github.nowakprojects.pwr.ai.lab2csp.nHetman

import java.util.*

/**
 * Container of chessboard
 * @param field the matrix with field
 */
data class Chessboard (val field: Array<BooleanArray>) {

    /**
     * Size of board size
     */
    val size = field.size

    /**
     * Method initialize the chessboard
     */
    fun initialize() {
        (0 until size).forEach { field[it].fill(false, 0, size) }
    }

    /**
     * Method check that field is ok in horizontal, vertical and all diagonals
     * @param w index of first dimen in board
     * @param h index of second dimen in board
     * @return boolean that queen on this place haven't got conflict with others
     */
    fun isFieldOk(w: Int, h: Int): Boolean {
        // horizontal
        (0 until size).forEach {
            if (field[w][it] && it != h)
                return false
        }
        // vertical
        (0 until size).forEach {
            if (field[it][h] && it != w)
                return false
        }
        // /
        val sum = w + h
        (0 until size).forEach {
            val p = sum - it
            if (it != w && p in 0 until size && field[it][p])
                return false
        }

        // \
        val distance = h - w
        (0 until size).forEach {
            val p = distance + it
            if (it != w && p in 0 until size && field[it][p])
                return false
        }

        return true
    }

    /**
     * Method check that filed is ok in diagonals
     * Warning: using this method have sens when method check the horizontal and vertical conditions are fulfilled
     * @param w index of first dimen in board
     * @param h index of second dimen in board
     * @return boolean that queen on this place haven't got conflict with others
     */
    fun isFieldDiagonalOk(w: Int, h: Int): Boolean {

        // /
        val sum = w + h
        (0 until size).forEach {
            val p = sum - it
            if (it != w && p in 0 until size && field[it][p])
                return false
        }

        // \
        val distance = h - w
        (0 until size).forEach {
            val p = distance + it
            if (it != w && p in 0 until size && field[it][p])
                return false
        }

        return true
    }

    /**
     * Method prints the board with queens
     */
    fun print() {
        field.forEach {
            it.forEach { if (it) print('D') else print('.') }
            println()
        }
        println()
    }

    /**
     * Equals method
     * @param other object to compare
     * @return boolean that equal
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chessboard

        if (!Arrays.equals(field, other.field)) return false

        return true
    }

    /**
     * Hashcode method
     * @return hashcode
     */
    override fun hashCode(): Int {
        return Arrays.hashCode(field)
    }

    /**
     * Method to clone the chessboard
     * @return copied chessboard
     */
    fun clone(): Chessboard {
        val newChessBoard = Chessboard.create(size)
        (0 until size).forEach { newChessBoard.field[it] = field[it].clone() }
        return newChessBoard
    }

    companion object {

        /**
         * Create the board with side size
         * @param n the side size of board
         * @return the new instance of chessboard
         */
        fun create(n: Int) = Chessboard(Array(n, { BooleanArray(n) }))
    }
}
