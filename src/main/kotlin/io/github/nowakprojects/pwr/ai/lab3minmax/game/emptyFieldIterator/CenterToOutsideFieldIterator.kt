package io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator

import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego


/**
 * Snail iterator, form outside to center
 */
class CenterToOutsideFieldIterator : EmptyFieldGenerator {

    /**
     * Method to return the iterator of empty fields
     * @param stratego game board
     * @return iterator of fields position
     */
    override fun getIterator(stratego: Stratego) =
            getSpiral(stratego.bordMatrix.size).filter { stratego.bordMatrix[it.first][it.second] == Stratego.FieldType.EMPTY }.iterator()

    private fun getSpiral(size: Int) =
            if (CenterToOutsideFieldIterator.cacheMap.containsKey(size))
                cacheMap[size]
            else {
                cacheMap[size] = IteratorHelper.spiralPrint(size).reversed()
                cacheMap[size]
            }!!

    companion object {

        val cacheMap = HashMap<Int, List<Pair<Int, Int>>>()
    }
}