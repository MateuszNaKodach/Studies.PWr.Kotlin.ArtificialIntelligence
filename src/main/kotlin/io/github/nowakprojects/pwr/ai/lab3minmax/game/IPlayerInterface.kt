package io.github.nowakprojects.pwr.ai.lab3minmax.game

/**
 * the player interface
 */
interface IPlayerInterface {

    /**
     * Method to make next move
     * @param move the position of next move
     */
    fun nextMove(move: Pair<Int, Int>)
}