package io.github.nowakprojects.pwr.ai.lab2csp.csp

interface Constraint<S:State<*,*>, V: Value<*,*>> {

    fun isSatisfiedFor(state:S, value: V): Boolean

}