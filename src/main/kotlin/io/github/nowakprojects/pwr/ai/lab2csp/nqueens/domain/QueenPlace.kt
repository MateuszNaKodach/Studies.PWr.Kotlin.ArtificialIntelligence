package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Value

class QueenPlace(x: Column, y: Row) : Value<Row, Column>(y, x) {

    fun getRow() = variableId
    fun getColumn() = value
    fun getX() = getColumn()
    fun getY() = getRow()
}