package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class NQueensResolver(private val specification: NQueensProblemSpecification) //w parencie dac ValueSelector
{

    private val constraints = specification.constraints
    private val selectors = specification.variables.map { it.id to QueenPlaceSelector(it) }
    private val freeColumnsByRows: MutableMap<Row, List<QueenPlace>> = mutableMapOf()

    fun findSolutionFromRow(y: Row) = findSolutionStartingInPoint(y, specification.initialState)

    fun findSolutionStartingInPoint(y: Row, state: Chessboard): QueensChessboard {
        if (y == state.n - 1) {
            return QueensChessboard(state, state.allQueensPlaced())
        }
        specification.variables.drop(y).forEach {
            val placesForRow = getNoViolatingConstraintsQueenPlacesForRow(state, it.id)
            if (placesForRow.isEmpty()) {
                return@findSolutionStartingInPoint QueensChessboard(state, false)
            }
            freeColumnsByRows[it.id] = placesForRow
        }
        val selectedQueenPlace = getSelectorForVariableId(y).selectFrom(freeColumnsByRows[y].orEmpty())
        return findSolutionStartingInPoint(y + 1, state.addQueenPlace(selectedQueenPlace))
    }

    private fun getNoViolatingConstraintsQueenPlacesForRow(state: Chessboard, y: Row) =
            specification.getColumnsForRow(y)
                    .map { x -> QueenPlace(x, y) }
                    .filter { place -> constraints.all { it.isSatisfiedFor(state, place) } }

    private fun getSelectorForVariableId(y: Row) = selectors[y].second

    private fun clearHistory() = selectors.forEach { it.second.clearHistory() }
}
