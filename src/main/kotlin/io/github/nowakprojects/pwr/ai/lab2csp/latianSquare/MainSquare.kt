package io.github.nowakprojects.pwr.ai.lab2csp.latianSquare

/**
 * Method to test the latian square solve algorithm
 */
fun main(args: Array<String>) {

    (1..5).forEach {
        println("Size: $it")
        val square = Square.create(it)
        val setB = BacktrackMethod(square).findSolution()
        println("solutions count: ${setB.size} ${setB.size * strong(it)}")
        println("-------------\n")
    }
}

/**
 * Easy method to calculate the strong
 */
fun strong(n: Int): Int = if (n == 0) 1 else (n * strong(n - 1))