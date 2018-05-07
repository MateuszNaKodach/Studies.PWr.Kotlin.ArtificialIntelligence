package io.github.nowakprojects.pwr.ai.lab2csp.csp

open class CSPResult<ID, V, VALUE : Value<ID, V>>(val values: List<VALUE>, val isSolution: Boolean)