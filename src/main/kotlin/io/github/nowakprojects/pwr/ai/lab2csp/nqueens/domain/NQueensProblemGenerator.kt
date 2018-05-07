package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.CSPSpecification
import io.github.nowakprojects.pwr.ai.lab2csp.csp.Constraint
import io.github.nowakprojects.pwr.ai.lab2csp.csp.Variable


typealias Column = Int
typealias Row = Int

class NQueensProblemGenerator(val n: Int) {

    fun generate(): NQueensProblemSpecification =
            CSPSpecification
                    .withVariables<Row,QueenInRow,QueenPlace,Chessboard>(generateVariables())
                    .andConstraints(NoOtherQueensInRow(), NoOtherQueensOnDiagonals())
                    .buildWithInitialState(Chessboard(n, emptyList())) as NQueensProblemSpecification


    private fun generateVariables() =
            (0 until n).map { x ->
                QueenInRow(x, (0 until n).toList())
            }.toList()
}

class NQueensProblemSpecification(variables: List<QueenInRow>, constraints: Set<Constraint<Chessboard, QueenPlace>>, initialState: Chessboard)
    : CSPSpecification<Row, QueenInRow, QueenPlace, Chessboard>(variables, constraints, initialState) {
}

