package io.github.nowakprojects.pwr.ai.lab3minmax.game.moveGenerator


/**
 * The interface giving next move
 */
interface IMoveGenerator {

    /**
     * Method giving next move
     * @return the next move position
     */
    fun nextMove(): Pair<Int, Int>
}