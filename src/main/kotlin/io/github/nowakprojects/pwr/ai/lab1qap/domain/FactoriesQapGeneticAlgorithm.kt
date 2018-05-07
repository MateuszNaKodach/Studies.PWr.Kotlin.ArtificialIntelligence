package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.*

class FactoriesQapGeneticAlgorithm(
        private val factoriesQapProblemSpecification: FactoriesQapProblemSpecification,
        epochLimit: Int,
        populationSize: Int,
        crossoverProbability: Double,
        mutationProbability: Double,
        selectionStrategy: SelectionStrategy<Int>,
        knownBestFitness: Double? = null
) : AbstractGeneticAlgorithm<Int>(
        false,
        GeneticAlgorithmSettings(epochLimit, populationSize, crossoverProbability, mutationProbability),
        PopulationCreator(factoriesQapProblemSpecification.getPossibleFactories(), populationSize),
        selectionStrategy,
        FactoriesQapCrossover(factoriesQapProblemSpecification.getPossibleFactories(), crossoverProbability),
        FactoriesQapMutation(mutationProbability),
        knownBestFitness
) {

    override fun computeFitness(chromosome: Chromosome<Int>): Double {
        var chromosomeFitness = 0.0
        val factoriesAmount = factoriesQapProblemSpecification.factoriesAmount
        val flowMatrix = factoriesQapProblemSpecification.flowMatrix
        val distanceMatrix = factoriesQapProblemSpecification.distanceMatrix
        val chromosomeGenes = chromosome.genes
        (0 until factoriesAmount).forEach { firstFactory ->
            (0 until factoriesAmount).forEach { secondFactory ->
                val requiredFlow = flowMatrix.get(firstFactory, secondFactory)
                val factoriesDistance = distanceMatrix.get(chromosomeGenes[firstFactory] - 1, chromosomeGenes[secondFactory] - 1)
                chromosomeFitness += (requiredFlow * factoriesDistance)
            }
        }
        return chromosomeFitness
    }

    override fun bestFitness(populationFitnessList: List<Double>) = populationFitnessList.min()!!

    override fun worstFitness(populationFitnessList: List<Double>): Double = populationFitnessList.max()!!

    override fun averageFitness(populationFitnessList: List<Double>): Double = populationFitnessList.average()

    override fun findBestSolution(fullPopulationStatsList: List<PopulationStats<Int>>): ChromosomeWithFitness<Int> {
        return fullPopulationStatsList.minBy { it.bestFitness }!!.bestChromosomeWithFitness
    }
}