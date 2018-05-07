package io.github.nowakprojects.pwr.ai.lab2csp.csp

import java.util.SortedSet

class CSPVariables<ID, V : Value<*>>(
        private val domainsByVariables: Map<ID, SortedSet<V>>
) {

    fun getVariableDomain(variableId: ID) = domainsByVariables[variableId]
}