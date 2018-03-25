package io.github.nowakprojects.pwr.ai.lab1.extensions

import java.util.*

fun Random.nextInt(min: Int = 0, bound: Int) = this.nextInt(bound - min) + min

fun Random.nextInt(min: Int = 0, max: Int, except: Int): Int {
    var random = except
    while (random == except) {
        random = nextInt(min, max)
    }
    return random
}