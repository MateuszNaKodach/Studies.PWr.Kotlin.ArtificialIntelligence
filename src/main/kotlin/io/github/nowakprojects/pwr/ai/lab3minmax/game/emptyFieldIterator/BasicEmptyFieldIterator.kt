package io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator

import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego


/**
 * The basic iterator. Iterate next rows.
 */
class BasicEmptyFieldIterator : EmptyFieldGenerator {

    /**
     * Method to return the iterator of empty fields
     * @param stratego game board
     * @return iterator of fields position
     */
    override fun getIterator(stratego: Stratego) = BasicIterator(stratego)

    /**
     * Implementation of BasicIterator
     */
    class BasicIterator(private val stratego: Stratego) : Iterator<Pair<Int, Int>> {
        /**
         * First dimension position
         */
        private var d1 = -1

        /**
         * Second dimension position
         */
        private var d2 = stratego.bordMatrix.size - 1

        /**
         * Count of all empty fields on board
         */
        private var size = stratego.bordMatrix.size * stratego.bordMatrix.size - stratego.filledFields

        /**
         * Check that iterator has next item
         * @return has next item
         */
        override fun hasNext() = size > 0

        /**
         * Return the next empty field position
         * @return empty field position
         */
        override fun next(): Pair<Int, Int> {
            size--
            findNext()
            return Pair(d1, d2)
        }

        /**
         * Finds the next empty field
         */
        private fun findNext() {
            plusOne()
            while (stratego.bordMatrix[d1][d2] != Stratego.FieldType.EMPTY) {
                plusOne()
            }
        }

        /**
         * Go to next position
         */
        private fun plusOne() {
            if (d2 == stratego.bordMatrix.size - 1) {
                d2 = 0
                d1++
            } else d2++
        }

    }

}