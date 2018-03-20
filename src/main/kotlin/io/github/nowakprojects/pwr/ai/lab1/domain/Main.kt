package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.PopulationCreator
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ProblemSpecificationProvider
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ResourcesFileProblemSpecificationProvider

//TODO: Exclude to GeneticAlgorithmCreator or something like that
val POPULATION_SIZE = 1000
val CROSSOVER_PROBABILITY = 0.8
val MUTATION_PROBABILITY = 0.008
val EPOCH_LIMIT = 10000

fun main(args: Array<String>) {
    val problemSpecificationProvider: ProblemSpecificationProvider = ResourcesFileProblemSpecificationProvider("/lab1/had12.dat")
    val problemSpecification = problemSpecificationProvider.provide()
    val factoriesQapGeneticAlgorithm = FactoriesQapGeneticAlgorithm(problemSpecification, EPOCH_LIMIT, POPULATION_SIZE, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY, knownBestFitness = 1652.0)
    val (chromosome, fitness) = factoriesQapGeneticAlgorithm.execute()
    println("After $EPOCH_LIMIT epochs, with population size: $POPULATION_SIZE")
    println("And probabilities: CROSSOVER: $CROSSOVER_PROBABILITY, MUTATION: $MUTATION_PROBABILITY")
    println("Best algorithm fitness is : $fitness")
    println("For chromosome: $chromosome")
}