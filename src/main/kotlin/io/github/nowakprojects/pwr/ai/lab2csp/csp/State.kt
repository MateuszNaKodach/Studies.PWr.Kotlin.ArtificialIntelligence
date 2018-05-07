package io.github.nowakprojects.pwr.ai.lab2csp.csp

open class State<ID, V:Value<ID,*>>(val values: List<V>) {

    fun getValueFor(variableId: ID): V? = values.firstOrNull { it.variableId == variableId }
}