package io.github.nowakprojects.pwr.ai.lab2csp.csp

interface Constraint<in T> {

    fun isSatisfiedFor(t: T):Boolean

}