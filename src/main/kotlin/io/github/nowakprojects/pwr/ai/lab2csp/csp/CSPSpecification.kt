package io.github.nowakprojects.pwr.ai.lab2csp.csp

import java.util.SortedSet

class CSPSpecification<ID, V : Value<*>> private constructor(val domainsByVariables: Map<ID, SortedSet<V>>, val constraints: Set<Constraint<V>>) {

    companion object {
        fun <ID, V : Value<*>> withVariables(domainsByVariables: Map<ID, SortedSet<V>>) = CSPSpecificationBuilder(domainsByVariables)
    }


    class CSPSpecificationBuilder<ID, V>(private val domainsByVariables: Map<ID, SortedSet<V>>) {
        val constraints: Set<Constraint<V>> = emptySet()


    }
}