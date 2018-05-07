package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

abstract class CrossoverStrategy<GENE>(
        protected val crossoverProbability: Double
) {

    abstract fun crossoverPopulation(population: Population<GENE>): Population<GENE>

}