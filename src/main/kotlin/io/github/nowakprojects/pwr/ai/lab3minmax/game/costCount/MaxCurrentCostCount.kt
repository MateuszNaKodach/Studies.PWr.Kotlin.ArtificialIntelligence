package io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount

import io.github.nowakprojects.pwr.ai.lab3minmax.game.PlayerEnum
import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego

/**
 * The points are calculated by the max current player points
 */
class MaxCurrentCostCount : CostCount {

    /**
     * Method to calculate cost
     * @param stratego the game board
     * @param playerEnum the current player
     * @return cost
     */
    override fun costCount(stratego: Stratego, playerEnum: PlayerEnum) =
            when (playerEnum) {
                PlayerEnum.PLAYER_1 -> stratego.getPoints().first
                PlayerEnum.PLAYER_2 -> stratego.getPoints().second
            }

}