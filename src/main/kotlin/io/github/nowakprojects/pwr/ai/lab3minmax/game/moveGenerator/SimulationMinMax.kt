package io.github.nowakprojects.pwr.ai.lab3minmax.game.moveGenerator

import io.github.nowakprojects.pwr.ai.lab3minmax.game.PlayerEnum
import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego
import io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator.EmptyFieldGenerator
import io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount.CostCount

/**
 * The simulation of AlfaBeta algorithm
 * @param stratego the board game with current state, the variable will be mutable during move search
 * @param mPlayerEnum the player, who make the move
 * @param treeDeep the deep of game tree
 * @param emptyFieldGenerator the generator of iterator by empty fields
 * @param costCount the object to calculate the board state
 */
class SimulationMinMax(
        private val stratego: Stratego,
        private val mPlayerEnum: PlayerEnum,
        private val treeDeep: Int = 3,
        private val emptyFieldGenerator: EmptyFieldGenerator,
        private val costCount: CostCount
)
    : IMoveGenerator {

    /**
     * Method giving next move
     * @return the next move position
     */
    override fun nextMove(): Pair<Int, Int> = searchMinMax()

    /**
     * Method to search next field using minMax algorithm
     * @return field position
     */
    private fun searchMinMax(): Pair<Int, Int> = searchMax(treeDeep).second

    /**
     * Method to search next field in max node using minMax algorithm
     * @param deep the deep to end searching
     * @return field position
     */
    private fun searchMax(deep: Int): Pair<Int, Pair<Int, Int>> =
            if (deep == 0 || stratego.isEndOfGame()) {
                Pair(costCount.costCount(stratego, mPlayerEnum), Pair(-1, -1))
            } else {
                var bestPair = Pair(Int.MIN_VALUE, Pair(-1, -1))
                emptyFieldGenerator.getIterator(stratego).forEach {
                    stratego.put(it.first, it.second)
                    val result = searchMin(deep - 1)

                    if (bestPair.first < result.first)
                        bestPair = Pair(result.first, Pair(it.first, it.second))

                    stratego.takeOffLast()
                }
                bestPair
            }

    /**
     * Method to search next field in min node using minMax algorithm
     * @param deep the deep to end searching
     * @return field position
     */
    private fun searchMin(deep: Int): Pair<Int, Pair<Int, Int>> =
            if (deep == 0 || stratego.isEndOfGame()) {
                Pair(costCount.costCount(stratego, mPlayerEnum), Pair(-1, -1))
            } else {
                var bestPair = Pair(Int.MAX_VALUE, Pair(-1, -1))
                emptyFieldGenerator.getIterator(stratego).forEach {
                    stratego.put(it.first, it.second)
                    val result = searchMax(deep - 1)

                    if (bestPair.first > result.first)
                        bestPair = Pair(result.first, Pair(it.first, it.second))

                    stratego.takeOffLast()
                }
                bestPair
            }
}