package io.github.nowakprojects.pwr.ai.lab2csp.latianSquare

import java.util.*

data class Square constructor(val field: Array<IntArray>) {

    /**
     * Size of Square
     */
    val size = field.size

    /**
     * Method to remove all values from square (default value is 0)
     */
    fun initialize(boolean: Boolean = true) {
        field.forEach { it.fill(0, 0, it.size) }
        if (boolean)
            (0 until size).forEach { field[it][0] = it + 1 }

    }

    /**
     * Check that the value at concrete place in square is correct
     * @param w first dimension
     * @param h second dimension
     * @param value checking value
     * @return boolean that value is correct
     */
    fun checkField(w: Int, h: Int, value: Int) = (0 until size).all {
        (field[w][it] != value || it == h) && (field[it][h] != value || it == w)
    }

    /**
     * Method prints the square in console
     */
    fun print() {
        field.forEach {
            it.forEach { print(it) }
            println()
        }
        println()
    }

    /**
     * Clone method
     * @return new instance
     */
    fun clone(): Square {
        val square = Square.create(size)
        (0 until size).forEach { square.field[it] = field[it].clone() }
        return square
    }

    /**
     * Equals method
     * @param other object to compare
     * @return boolean that equal
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Square

        if (!Arrays.equals(field, other.field)) return false
        if (size != other.size) return false

        return true
    }

    /**
     * Hashcode method
     * @return hashcode
     */
    override fun hashCode(): Int {
        var result = Arrays.hashCode(field)
        result = 31 * result + size
        return result
    }

    companion object {

        /**
         * Function to create Square with parametrize size
         * @param n square size
         * @return the new Square object
         */
        fun create(n: Int) = Square(Array(n, { IntArray(n) }))
    }
}