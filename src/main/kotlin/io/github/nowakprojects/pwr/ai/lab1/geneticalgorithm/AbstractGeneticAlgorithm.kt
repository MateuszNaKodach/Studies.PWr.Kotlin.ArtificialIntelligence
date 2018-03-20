package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.domain.GeneticAlgorithmBestSolution

abstract class AbstractGeneticAlgorithm<GENE>(
        val epochLimit: Int,
        val populationSize: Int,
        val crossoverProbability: Double,
        val mutationProbability: Double,
        val populationCreator: PopulationCreator<GENE>,
        val selectionStrategy: SelectionStrategy<GENE>,
        val crossoverStrategy: CrossoverStrategy<GENE>,
        val mutationStrategy: MutationStrategy<GENE>,
        val knownBestFitness: Double? = null
) {

    fun execute(): GeneticAlgorithmBestSolution<GENE,Double> {
        val algorithmStart = System.currentTimeMillis()
        val bestChromosomes = mutableListOf<ChromosomeWithFitness<GENE>>()
        var population = populationCreator.createRandomPopulation()
        var pastEpochs = 0
        for (epoch in (1..epochLimit)) {
            if (bestChromosomes.map { it.fitness }.contains(knownBestFitness)) {
                pastEpochs = epoch - 1
                break
            }
            val populationFitnessList = computeFitnessForPopulation(population)
            val normalizedFitnessList = normaliseFitnessList(populationFitnessList)
            val chromosomeWithNormalizedFitnessList =
                    (0 until population.size).map { ChromosomeWithFitness<GENE>(population.chromosomes[it], normalizedFitnessList[it]) }
            val selectedPopulation = selectionStrategy.selectNewPopulation(chromosomeWithNormalizedFitnessList)
            val crossedPopulation = crossoverStrategy.crossoverPopulation(selectedPopulation)
            val mutatedPopulation = mutationStrategy.mutatePopulation(crossedPopulation)

            val bestFitness = bestFitness(populationFitnessList)
            val bestChromosomeIndex = populationFitnessList.indexOf(bestFitness)
            val bestChromosome = population.get(bestChromosomeIndex)
            println("Epoch: $epoch, Population fitness average: ${populationFitnessList.average()}, Best fitness: $bestFitness, Best chromosome: $bestChromosome")
            bestChromosomes.add(ChromosomeWithFitness(bestChromosome, bestFitness))
            population = mutatedPopulation
        }
        val bestSolution = bestChromosomes.minBy { it.fitness }!!
        val algorithmEnd = System.currentTimeMillis()
        return GeneticAlgorithmBestSolution(bestSolution.chromosome, bestSolution.fitness, pastEpochs, algorithmEnd-algorithmStart, bestChromosomes)
    }

    private fun computeFitnessForPopulation(population: Population<GENE>) =
            population.chromosomes.map { computeFitness(it) }

    private fun normaliseFitnessList(fitnessList: List<Double>): List<Double> {
        val fitnessListSum = fitnessList.sum()
        return fitnessList
                .map { it / fitnessListSum }
    }

    //TODO: Dzielenie cos z maksymalizacja/ minimalizacja
    abstract fun computeFitness(chromosome: Chromosome<GENE>): Double

    abstract fun bestFitness(populationFitnessList: List<Double>): Double
}