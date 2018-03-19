package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

abstract class GeneticAlgorithmConfiguration<GENE>(
        val populationSize: Int,
        val crossoverProbability: Double,
        val mutationProbability: Double,
        val populationCreator: PopulationCreator<GENE>,
        val selectionStrategy: SelectionStrategy<GENE>,
        val crossoverStrategy: CrossoverStrategy<GENE>
) {

    fun execute() {
        val population = populationCreator.createRandomPopulation()
        val populationFitnessList = computeFitnessForPopulation(population)
        val chromosomeWithFitnessList =
                (0..population.size).map { ChromosomeWithFitness<GENE>(population.chromosomes[it], populationFitnessList[it]) }

        val selectionPopulation = selectionStrategy.selectNewPopulation(chromosomeWithFitnessList)
        val crossoverPopulation = crossoverStrategy.crossoverPopulation(selectionPopulation)
    }

    private fun computeFitnessForPopulation(population: Population<GENE>) =
            normaliseFitnessList(population.chromosomes.map { computeFitness(it) })

    private fun normaliseFitnessList(fitnessList: List<Double>): List<Double> {
        val fitnessListSum = fitnessList.sum()
        return fitnessList
                .map { it / fitnessListSum }
    }

    //TODO: Dzielenie cos z maksymalizacja/ minimalizacja
    abstract fun computeFitness(chromosome: Chromosome<GENE>): Double

    abstract fun crossover(parent1: Chromosome<GENE>, parent2: Chromosome<GENE>): Pair<Chromosome<GENE>, Chromosome<GENE>>
}