package io.github.nowakprojects.pwr.ai.lab2csp.csp

interface Constraint<ID, V, VALUE : Value<ID, V>, S : State<ID, V, VALUE>> {

    fun isSatisfiedFor(state: S, value: VALUE): Boolean

}