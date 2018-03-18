package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ProblemSpecificationProvider
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ResourcesFileProblemSpecificationProvider

//TODO: Exclude to GeneticAlgorithmCreator or something like that
val POPULATION_SIZE = 1000
val CROSSOVER_PROBABILITY = 0.8
val MUTATION_PROBABILITY = 0.008

fun main(args: Array<String>) {
    val problemSpecificationProvider: ProblemSpecificationProvider = ResourcesFileProblemSpecificationProvider()
    val problemSpecification = problemSpecificationProvider.provide()
    val populationCreator = PopulationCreator<Int>(
            problemSpecification.getPossibleFactories(),
            POPULATION_SIZE
    )
}