package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.*


typealias Column = Int
typealias Row = Int

class NQueensProblemGenerator(val n: Int) {

    fun generate(): NQueensProblemSpecification =
            NQueensProblemSpecification
                    .withVariables(generateVariables())
                    .andConstraints(NoOtherQueensInRow(), NoOtherQueensInColumn(), NoOtherQueensOnDiagonals())
                    .buildWithInitialState(Chessboard(n, emptyList()))


    private fun generateVariables() =
            (0 until n).map { x ->
                QueenInRow(x, (0 until n).toList())
            }.toList()
}

class NQueensProblemSpecification(variables: List<QueenInRow>, constraints: Set<Constraint<Row, Column, QueenPlace, Chessboard>>, initialState: Chessboard)
    : CSPSpecification<Row, Column, QueenInRow, QueenPlace, Chessboard>(variables, constraints, initialState) {

    fun getColumnsForRow(row: Row) = getVariableDomain(row)

    companion object {
        fun withVariables(queenInRows: List<QueenInRow>) = NQueensProblemSpecificationBuilder(queenInRows)
    }
}

class NQueensProblemSpecificationBuilder(queenInRows: List<QueenInRow>)
    : CSPSpecificationBuilder<Row, Column, QueenInRow, QueenPlace, Chessboard, NQueensProblemSpecification>(queenInRows) {

    override fun create(variables: List<QueenInRow>, constraints: Set<Constraint<Row, Column, QueenPlace, Chessboard>>, initialState: Chessboard) = NQueensProblemSpecification(variables, constraints, initialState)
}

