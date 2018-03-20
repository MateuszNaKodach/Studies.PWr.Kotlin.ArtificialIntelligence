package io.github.nowakprojects.pwr.ai.lab1.extensions

import java.util.*

fun <E> List<E>.random(random: java.util.Random = Random()): E? = if (size > 0) get(random.nextInt(size)) else null

fun <E> List<E>.takeRandom(n: Int = 1, random: java.util.Random = Random()): List<E> =
        if (this.isEmpty()) {
            emptyList()
        } else {
            (0 until n).map { this.random()!! }
        }
