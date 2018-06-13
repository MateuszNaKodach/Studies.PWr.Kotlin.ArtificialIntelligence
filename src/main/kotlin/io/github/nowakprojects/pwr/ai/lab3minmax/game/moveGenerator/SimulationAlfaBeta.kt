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
class SimulationAlfaBeta(
        private val stratego: Stratego,
        private val mPlayerEnum: PlayerEnum,
        private val treeDeep: Int = 8,
        private val emptyFieldGenerator: EmptyFieldGenerator,
        private val costCount: CostCount
) : IMoveGenerator {

    /**
     * Method giving next move
     * @return the next move position
     */
    override fun nextMove() = maxValue(treeDeep, Int.MIN_VALUE, Int.MAX_VALUE).second

    /**
     * Method to search next field in max node using alfaBeta algorithm
     * @param deep the deep to end searching
     * @param alfa alfa parameter
     * @param beta beta parameter
     * @return field position
     */
    private fun maxValue(deep: Int, alfa: Int, beta: Int): Pair<Int, Pair<Int, Int>> {

        if (deep == 0 || stratego.isEndOfGame())
            return Pair(costCount.costCount(stratego, mPlayerEnum), Pair(-1, -1))

        var a = alfa
        var v = Int.MIN_VALUE

        var bestMove = Pair(-1, -1)

        emptyFieldGenerator.getIterator(stratego).forEach {
            stratego.put(it.first, it.second)

            val result = minValue(deep - 1, a, beta)

            if (result.first > v) {
                v = result.first
                bestMove = it
            }

            if (result.first >= beta) {
                stratego.takeOffLast()
                return Pair(v, it)
            }

            if (result.first > a)
                a = result.first

            stratego.takeOffLast()
        }
        return Pair(v, bestMove)
    }


    /**
     * Method to search next field in min node using alfaBeta algorithm
     * @param deep the deep to end searching
     * @param alfa alfa parameter
     * @param beta beta parameter
     * @return field position
     */
    private fun minValue(deep: Int, alfa: Int, beta: Int): Pair<Int, Pair<Int, Int>> {
        if (deep == 0 || stratego.isEndOfGame())
            return Pair(costCount.costCount(stratego, mPlayerEnum), Pair(-1, -1))

        var b = beta
        var v = Int.MAX_VALUE

        var bestMove = Pair(-1, -1)

        emptyFieldGenerator.getIterator(stratego).forEach {

            stratego.put(it.first, it.second)

            val result = maxValue(deep - 1, alfa, b)

            if (result.first < v) {
                v = result.first
                bestMove = it
            }

            if (result.first <= alfa) {
                stratego.takeOffLast()
                return Pair(v, it)
            }

            if (result.first < b)
                b = result.first

            stratego.takeOffLast()
        }
        return Pair(v, bestMove)
    }
}