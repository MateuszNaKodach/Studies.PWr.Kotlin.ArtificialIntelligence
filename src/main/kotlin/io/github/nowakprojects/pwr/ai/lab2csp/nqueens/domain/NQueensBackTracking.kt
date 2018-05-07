package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

class NQueensBackTracking(private val specification: NQueensProblemSpecification) //w parencie dac ValueSelector
{

    private val constraints = specification.constraints
    val solutions: MutableList<QueensChessboard> = mutableListOf();
    private val queenPlaces: MutableList<QueenPlace> = mutableListOf()

    fun solve() = findSolutionStartingInPoint(0, QueenPlace(0, 0), specification.initialState)

    fun findSolutionStartingInPoint(placed: Int, currentField: QueenPlace, state: Chessboard) {
        state.prettyPrint()
        if (queenPlaces.size == state.n) {
            solutions.add(QueensChessboard(Chessboard(8, queenPlaces), true))
            queenPlaces.clear()
        } else {
            specification.variables.drop(currentField.getY()).forEach { y ->
                y.domain.drop(currentField.getX()).forEach { x ->
                    val queenPlace = QueenPlace(x, y.id)
                    if (constraints.all { it.isSatisfiedFor(Chessboard(8, queenPlaces), queenPlace) }) {
                        queenPlaces.add(queenPlace)
                        findSolutionStartingInPoint(placed + 1, queenPlace, Chessboard(8,queenPlaces))
                    }
                }
            }
        }
        /*if (y == state.n - 1) {
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
        return findSolutionStartingInPoint(y + 1, state.addQueenPlace(selectedQueenPlace))*/
    }

    private fun getNoViolatingConstraintsQueenPlacesForRow(state: Chessboard, y: Row) =
            specification.getColumnsForRow(y)
                    .map { x -> QueenPlace(x, y) }
                    .filter { place -> constraints.all { it.isSatisfiedFor(state, place) } }

}
