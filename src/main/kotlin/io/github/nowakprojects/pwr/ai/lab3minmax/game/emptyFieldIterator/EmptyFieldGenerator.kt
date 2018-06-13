package io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator

import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego


/**
 * Interface of empty field generator
 */
interface EmptyFieldGenerator {

    /**
     * Method to return the iterator of empty fields
     * @param stratego game board
     * @return iterator of fields position
     */
    fun getIterator(stratego: Stratego): Iterator<Pair<Int, Int>>
}