package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Value

class QueenPlace(x: Row, y: Column) : Value<Row, Column>(x, y) {

    fun getRow() = variableId
    fun getColumn() = value
    fun getX() = getColumn()
    fun getY() = getRow()
}