package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

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

    fun execute(): ChromosomeWithFitness<GENE> {
        val bestChromosomes = mutableListOf<ChromosomeWithFitness<GENE>>()
        var population = populationCreator.createRandomPopulation()
        for (epoch in (1..epochLimit)) {
            if (bestChromosomes.map { it.fitness }.contains(knownBestFitness)) {
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
        return bestChromosomes.minBy { it.fitness }!!
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