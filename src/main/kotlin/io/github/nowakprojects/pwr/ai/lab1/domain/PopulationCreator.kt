package io.github.nowakprojects.pwr.ai.lab1.domain

abstract class PopulationCreator<T>(
        private val genes: Int,
        private val chromosomes: Int
) {

    fun createRandomPopulation() =
            Population<T>((0..chromosomes).map { generateRandomValue() }.shuffled().toTypedArray())
    

    abstract fun generateRandomValue(): T
}