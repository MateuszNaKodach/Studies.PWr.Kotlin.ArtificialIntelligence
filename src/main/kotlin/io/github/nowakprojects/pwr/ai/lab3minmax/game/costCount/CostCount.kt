package io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount

import io.github.nowakprojects.pwr.ai.lab3minmax.game.PlayerEnum
import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego

/**
 * The interface to calculate the board state
 */
interface CostCount {

    /**
     * Method to calculate cost
     * @param stratego the game board
     * @param playerEnum the current player
     * @return cost
     */
    fun costCount(stratego: Stratego, playerEnum: PlayerEnum): Int
}