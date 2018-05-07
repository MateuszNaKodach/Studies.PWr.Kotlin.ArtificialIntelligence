package io.github.nowakprojects.pwr.ai.lab2csp.csp

open class State<ID, V, VALUE : Value<ID, V>>(val values: List<VALUE>) {

    fun getValueFor(variableId: ID): VALUE? = values.firstOrNull { it.variableId == variableId }
}