package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class NQueensForwardchecking(private val specification: NQueensProblemSpecification) //w parencie dac ValueSelector
{

    private val constraints = specification.constraints
    private val selectors = specification.variables.map { it.id to QueenPlaceSelector(it) }

    fun findSolutionFromRow(x: Column, y: Row) = findSolutionStartingInPoint(x, y, specification.initialState)


    fun findSolutionStartingInPoint(x: Column, y: Row, state: Chessboard): Chessboard {
        state.prettyPrint()
        if (state.allQueensPlaced()) {
            return state
        }
        val availableColumnsForRow = getNoViolatingConstraintsColumnsForRow(state, y)
        if (availableColumnsForRow.isNotEmpty() && x < state.n) {
            val selectedQueenPlace = getSelectorForVariableId(y).selectFrom(availableColumnsForRow)
            return findSolutionStartingInPoint(y + 1, x, state.addQueenPlace(selectedQueenPlace))
        } else {
            return findSolutionStartingInPoint(y - 1, x, state.removeLastQueenPlace())
        }
    }

    private fun getNoViolatingConstraintsColumnsForRow(state: Chessboard, y: Row) =
            specification.getColumnsForRow(y)
                    .map { x -> QueenPlace(x, y) }
                    .filter { place -> constraints.all { it.isSatisfiedFor(state, place) } }

    private fun getSelectorForVariableId(y: Row) = selectors[y].second

    private fun clearHistory() = selectors.forEach { it.second.clearHistory() }
}
