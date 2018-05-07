package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class NQueensResolver(private val specification: NQueensProblemSpecification) //w parencie dac ValueSelector
{

    private val constraints = specification.constraints
    private val selectors = specification.variables.map { it.id to QueenPlaceSelector(it) }

    fun findSolutionFromRow(x: Row) = findSolutionStartingInRow(x, specification.initialState)


    fun findSolutionStartingInRow(x: Row, state: Chessboard): Chessboard {
        state.prettyPrint()
        if (state.allQueensPlaced()) {
            return state
        }
        val availableColumnsForRow = getNoViolatingConstraintsColumnsForRow(state, x)
        if (availableColumnsForRow.isNotEmpty() && x < state.n) {
            val selectedQueenPlace = getSelectorForVariableId(x).selectFrom(availableColumnsForRow)
            return findSolutionStartingInRow(x + 1, state.addQueenPlace(selectedQueenPlace))
        } else {
            return findSolutionStartingInRow(x - 1, state.removeLastQueenPlace())
        }
    }

    private fun getNoViolatingConstraintsColumnsForRow(state: Chessboard, x: Row) =
            specification.getColumnsForRow(x)
                    .map { y -> QueenPlace(x, y) }
                    .filter { place -> constraints.all { it.isSatisfiedFor(state, place) } }

    private fun getSelectorForVariableId(x: Row) = selectors[x].second
}
