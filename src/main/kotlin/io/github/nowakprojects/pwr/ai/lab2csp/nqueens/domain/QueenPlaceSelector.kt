package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class QueenPlaceSelector(val queenRow: QueenInRow) {

    val history: MutableList<QueenPlace> = mutableListOf<QueenPlace>()

    fun selectFrom(availableRows: List<QueenPlace>): QueenPlace {
        val selected = availableRows.filter { !history.contains(it) }[0]
        history.add(selected)
        return selected
    }

    fun clearHistory() {
        history.clear()
    }
}