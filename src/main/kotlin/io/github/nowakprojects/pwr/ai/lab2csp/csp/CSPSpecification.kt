package io.github.nowakprojects.pwr.ai.lab2csp.csp


abstract class CSPSpecification<ID, V, VARIABLE : Variable<ID, V>, VALUE : Value<ID, V>, STATE : State<ID, V, VALUE>>
constructor(val variables: List<VARIABLE>, val constraints: Set<Constraint<ID, V, VALUE, STATE>>, val initialState: STATE) {


    fun getVariableDomain(variableId: ID) = variables.first { it.id == variableId }.domain
}

abstract class CSPSpecificationBuilder<ID, V, VARIABLE : Variable<ID, V>, VALUE : Value<ID, V>, STATE : State<ID, V, VALUE>,
        T : CSPSpecification<ID, V, VARIABLE, VALUE, STATE>>(private val variables: List<VARIABLE>) {
    private val constraints: MutableSet<Constraint<ID, V, VALUE, STATE>> = mutableSetOf()

    fun <C : Constraint<ID, V, VALUE, STATE>> andConstraints(vararg constraints: C) =
            this.apply { this.constraints.addAll(constraints) }


    fun buildWithInitialState(initialState: STATE): T {
        if (variables.size < 2) {
            throw IllegalStateException("CSP have to specified as least 2 variables!")
        }
        if (constraints.isEmpty()) {
            throw IllegalStateException("CSP have to specified as least 1 constraint!")
        }
        return create(variables, constraints, initialState)
    }

    abstract fun create(variables: List<VARIABLE>, constraints: Set<Constraint<ID, V, VALUE, STATE>>, initialState: STATE): T
}