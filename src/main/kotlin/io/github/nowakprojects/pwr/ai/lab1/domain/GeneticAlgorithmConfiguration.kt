package io.github.nowakprojects.pwr.ai.lab1.domain

abstract class GeneticAlgorithmConfiguration<GENE>(
        val populationSize: Int,
        val crossoverProbability: Double,
        val mutationProbability: Double,
        val populationCreator: PopulationCreator<GENE>
) {

    fun execute() {
        val population = populationCreator.createRandomPopulation()
        val populationFitnessList = normaliseFitnessList(computeFitnessForPopulation(population))

    }

    private fun normaliseFitnessList(fitnessList: List<Double>): List<Double> {
        val fitnessListSum = fitnessList.sum()
        return fitnessList
                .map { it / fitnessListSum }
    }

    fun computeFitnessForPopulation(population: Population<GENE>) = population.chromosomes.map { computeFitness(it) }

    //TODO: Dzielenie cos z maksymalizacja/ minimalizacja
    abstract fun computeFitness(chromosome: Chromosome<GENE>): Double
}