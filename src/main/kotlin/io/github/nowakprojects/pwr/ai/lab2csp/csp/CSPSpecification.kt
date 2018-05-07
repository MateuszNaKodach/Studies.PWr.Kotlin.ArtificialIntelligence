package io.github.nowakprojects.pwr.ai.lab2csp.csp


open class CSPSpecification<ID, V : Variable<ID, *>, E : Value<ID, *>, S : State<*, *>>
protected constructor(val variables: List<V>, val constraints: Set<Constraint<*, *>>, val initialState: S) {


    fun getVariableDomain(variableId: ID) = variables.first { it.id == variableId }.domain


    companion object {
        fun <ID, V : Variable<ID, *>,  E : Value<ID, *>, S : State<*, *>> withVariables(variables: List<V>) = CSPSpecificationBuilder<ID,V,E,S>(variables)
    }

    class CSPSpecificationBuilder<ID, V : Variable<ID, *>, E : Value<ID, *>, S : State<*, *>>(private val variables: List<V>) {
        private val constraints: MutableSet<Constraint<*, *>> = mutableSetOf()

        fun <C : Constraint<*, *>> andConstraints(vararg constraints: C) =
                this.apply { this.constraints.addAll(constraints) }


        fun buildWithInitialState(initialState: S): CSPSpecification<ID, V, E, S> {
            if (variables.size < 2) {
                throw IllegalStateException("CSP have to specified as least 2 variables!")
            }
            if (constraints.isEmpty()) {
                throw IllegalStateException("CSP have to specified as least 1 constraint!")
            }
            return CSPSpecification(variables, constraints, initialState)
        }
    }
}