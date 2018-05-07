package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.Variable
import java.util.SortedSet

class QueenInRow(row: Row, availableColumns: List<Column>) : Variable<Int, Int>(row, availableColumns)