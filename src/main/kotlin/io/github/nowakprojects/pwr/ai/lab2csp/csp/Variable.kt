package io.github.nowakprojects.pwr.ai.lab2csp.csp

import java.util.SortedSet


open class Variable<ID, V>(val id: ID, val domain: List<V>)