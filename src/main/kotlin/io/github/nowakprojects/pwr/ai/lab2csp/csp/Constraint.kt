package io.github.nowakprojects.pwr.ai.lab2csp.csp

interface Constraint<V: Value<*,*>> {

    fun isSatisfiedFor(value: V): Boolean

}