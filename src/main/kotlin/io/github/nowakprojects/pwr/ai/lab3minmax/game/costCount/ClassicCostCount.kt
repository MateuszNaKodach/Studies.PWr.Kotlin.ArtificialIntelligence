package io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount

import io.github.nowakprojects.pwr.ai.lab3minmax.game.PlayerEnum
import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego


/**
 * The points are calculated by the subtract the opposite player points from current
 */
class ClassicCostCount : CostCount {

    /**
     * Method to calculate cost
     * @param stratego the game board
     * @param playerEnum the current player
     * @return cost
     */
    override fun costCount(stratego: Stratego, playerEnum: PlayerEnum) = stratego.getPoints()
            .run {
                if (playerEnum == PlayerEnum.PLAYER_1)
                    this.first - second
                else second - first
            }
}