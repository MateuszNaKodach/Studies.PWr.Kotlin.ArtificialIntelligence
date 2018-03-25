package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.GeneticAlgorithmSolutionCsvFileWriter
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.RouletteSelection
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.SelectionStrategy
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.TournamentSelection
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ProblemSpecificationProvider
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ResourcesFileProblemSpecificationProvider

//TODO: Exclude to GeneticAlgorithmCreator or something like that
const val POPULATION_SIZE = 10000
const val CROSSOVER_PROBABILITY = 0.8
const val MUTATION_PROBABILITY = 0.008
const val EPOCH_LIMIT = 100

fun main(args: Array<String>) {
    val resourcePath = "/lab1/had20.dat"
   // val knownBestFitness = 1652.0
    val problemSpecificationProvider: ProblemSpecificationProvider = ResourcesFileProblemSpecificationProvider(resourcePath)
    val problemSpecification = problemSpecificationProvider.provide()
    val factoriesQapGeneticAlgorithm = FactoriesQapGeneticAlgorithm(
            problemSpecification,
            EPOCH_LIMIT,
            POPULATION_SIZE,
            CROSSOVER_PROBABILITY,
            MUTATION_PROBABILITY,
            TournamentSelection<Int>(tournamentParticipantsAmount = 5, elitism = true, selectionGoal = SelectionStrategy.SelectionGoal.MINIMIZE_FITNESS),
            //RouletteSelection<Int>(elitism = true, selectionGoal = SelectionStrategy.SelectionGoal.MINIMIZE_FITNESS),
            null
    )

    val algorithmResult = factoriesQapGeneticAlgorithm.execute()
    val (bestSolution, fullPopulationStatsList, passedEpochs, executionTimeMillis, selectionStrategyName, settings) = algorithmResult
    println("ALGORITHM HAS FINISH! Execution takes: $executionTimeMillis ms")
    println("After $passedEpochs epochs (limit was: ${settings.epochLimit}), with population size: ${settings.populationSize}")
    println("And probabilities - CROSSOVER: ${settings.crossoverProbability} - MUTATION: ${settings.mutationProbability}")
    println("Best solution is : fitness = ${bestSolution.fitness} for chromosome = ${bestSolution.chromosome}")

    GeneticAlgorithmSolutionCsvFileWriter<Int,Double>().writeToCsv(algorithmResult)
}