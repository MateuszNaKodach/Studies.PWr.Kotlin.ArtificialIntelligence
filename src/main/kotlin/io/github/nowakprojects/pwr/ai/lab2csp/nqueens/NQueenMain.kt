package io.github.nowakprojects.pwr.ai.lab2csp.nqueens

import io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain.*

fun main(args: Array<String>) {
    val N = 8

    val specification = NQueensProblemGenerator(N).generate()
    /*val currentState = specification.initialState
    currentState.prettyPrint()
    currentState.addQueenPlace(QueenPlace(2,1)).prettyPrint()
    currentState.addQueenPlace(QueenPlace(1,2)).prettyPrint()
    currentState.prettyPrint()
*/
    val resolver = NQueensResolver(NQueensProblemGenerator(N).generate())
    val solution = resolver.findSolutionFromRow(0, 1)
    solution.prettyPrint()
}

fun findSolutionStartingIn(x: Row, y: Column) {

}