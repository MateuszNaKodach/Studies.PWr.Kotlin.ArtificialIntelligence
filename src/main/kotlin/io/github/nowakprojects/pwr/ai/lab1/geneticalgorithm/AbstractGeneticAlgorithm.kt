package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.domain.GeneticAlgorithmBestSolution

abstract class AbstractGeneticAlgorithm<GENE>(
        val useFitnessCacheMap: Boolean = false,
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

    private val fitnessCache = HashMap<Chromosome<GENE>, Double>()

    fun execute(): GeneticAlgorithmBestSolution<GENE, Double> {
        val algorithmStart = System.currentTimeMillis()
        val bestChromosomes = mutableListOf<ChromosomeWithFitness<GENE>>()
        var population = populationCreator.createRandomPopulation()
        var pastEpochs = 0
        for (epoch in (1..epochLimit)) {
            val populationFitnessList = computeFitnessForPopulation(population)
            val selectedPopulation = selectionStrategy.selectNewPopulation(population, populationFitnessList)
            val crossedPopulation = crossoverStrategy.crossoverPopulation(selectedPopulation)
            val mutatedPopulation = mutationStrategy.mutatePopulation(crossedPopulation)

            val bestFitness = bestFitness(populationFitnessList)
            val bestChromosomeIndex = populationFitnessList.indexOf(bestFitness)
            val bestChromosome = population.get(bestChromosomeIndex)
            println("Epoch: $epoch, Population fitness average: ${populationFitnessList.average()}, Best fitness: $bestFitness, Best chromosome: $bestChromosome")
            bestChromosomes.add(ChromosomeWithFitness(bestChromosome, bestFitness))
            population = mutatedPopulation

            if (isSolutionKnown() && bestChromosomes.map { it.fitness }.contains(knownBestFitness)) {
                pastEpochs = epoch
                break
            }
        }
        val bestSolution = bestChromosomes.minBy { it.fitness }!!
        val algorithmEnd = System.currentTimeMillis()
        return GeneticAlgorithmBestSolution(bestSolution.chromosome, bestSolution.fitness, pastEpochs, algorithmEnd - algorithmStart, bestChromosomes)
    }

    private fun computeFitnessForPopulation(population: Population<GENE>) =
            population.chromosomes.map { if (useFitnessCacheMap) getFitnessForChromosome(it) else computeFitness(it) }


    private fun getFitnessForChromosome(chromosome: Chromosome<GENE>): Double =
            fitnessCache.getOrPut(chromosome, { computeFitness(chromosome) })

    private fun isSolutionKnown() = knownBestFitness != null

    protected fun isContainsBestSolutionChromosome(){

    }

    //TODO: Dzielenie cos z maksymalizacja/ minimalizacja
    abstract fun computeFitness(chromosome: Chromosome<GENE>): Double

    abstract fun bestFitness(populationFitnessList: List<Double>): Double

    abstract fun worstFitness(populationFitnessList: List<Double>): Double

    abstract fun averageFitness(populationFitnessList: List<Double>): Double

}