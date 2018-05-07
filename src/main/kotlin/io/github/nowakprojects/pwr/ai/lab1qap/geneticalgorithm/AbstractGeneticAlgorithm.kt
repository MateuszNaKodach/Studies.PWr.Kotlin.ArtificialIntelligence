package io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1qap.domain.GeneticAlgorithmSolution

abstract class AbstractGeneticAlgorithm<GENE>(
        val useFitnessCacheMap: Boolean = false,
        val geneticAlgorithmSettings: GeneticAlgorithmSettings,
        val populationCreator: PopulationCreator<GENE>,
        val selectionStrategy: SelectionStrategy<GENE>,
        val crossoverStrategy: CrossoverStrategy<GENE>,
        val mutationStrategy: MutationStrategy<GENE>,
        val knownBestFitness: Double? = null
) {

    private val fitnessCache = HashMap<Chromosome<GENE>, Double>()

    fun execute(): GeneticAlgorithmSolution<GENE, Double> {
        val (epochLimit, populationSize, crossoverProbability, mutationProbability) = geneticAlgorithmSettings
        val algorithmStart = System.currentTimeMillis()
        val fullPopulationStatsList = mutableListOf<PopulationStats<GENE>>()
        var currentPopulation = populationCreator.createRandomPopulation()
        var pastEpochs = 0
        for (epoch in (1..epochLimit)) {
            val populationFitnessList = computeFitnessForPopulation(currentPopulation)
            val populationStats = createPopulationStats(currentPopulation.copy(), populationFitnessList)
            fullPopulationStatsList.add(populationStats)
            println("Epoch: $epoch\n$populationStats")

            val selectedPopulation = selectionStrategy.selectNewPopulation(currentPopulation, populationFitnessList)
            val crossedPopulation = crossoverStrategy.crossoverPopulation(selectedPopulation)
            val mutatedPopulation = mutationStrategy.mutatePopulation(crossedPopulation)


            pastEpochs = epoch
            if (isSolutionKnown() && populationStats.bestFitness == knownBestFitness) {
                break
            }
            currentPopulation = mutatedPopulation.copy(chromosomes = mutatedPopulation.chromosomes.toMutableList().apply { set(0,populationStats.bestChromosome) })
        }
        val executionTimeMillis = System.currentTimeMillis() - algorithmStart
        return GeneticAlgorithmSolution(
                findBestSolution(fullPopulationStatsList),
                fullPopulationStatsList,
                pastEpochs,
                executionTimeMillis,
                selectionStrategy.javaClass.simpleName,
                geneticAlgorithmSettings
        )
    }

    private fun createPopulationStats(currentPopulation: Population<GENE>, populationFitnessList: List<Double>): PopulationStats<GENE> {
        val bestFitness = bestFitness(populationFitnessList)
        val bestChromosomeIndex = populationFitnessList.indexOf(bestFitness)
        val bestChromosome = currentPopulation.get(bestChromosomeIndex)
        val averageFitness = averageFitness(populationFitnessList)
        val worstFitness = worstFitness(populationFitnessList)
        val worstChromosomeIndex = populationFitnessList.indexOf(worstFitness)
        val worstChromosome = currentPopulation.get(worstChromosomeIndex)
        return PopulationStats(ChromosomeWithFitness(bestChromosome, bestFitness), averageFitness, ChromosomeWithFitness(worstChromosome, worstFitness))
    }

    abstract fun findBestSolution(fullPopulationStatsList: List<PopulationStats<GENE>>): ChromosomeWithFitness<GENE>

    private fun computeFitnessForPopulation(population: Population<GENE>) =
            population.chromosomes.map { if (useFitnessCacheMap) getFitnessForChromosome(it) else computeFitness(it) }


    private fun getFitnessForChromosome(chromosome: Chromosome<GENE>): Double =
            fitnessCache.getOrPut(chromosome, { computeFitness(chromosome) })

    private fun isSolutionKnown() = knownBestFitness != null

    protected fun isContainsBestSolutionChromosome() {

    }

    abstract fun computeFitness(chromosome: Chromosome<GENE>): Double

    abstract fun bestFitness(populationFitnessList: List<Double>): Double

    abstract fun worstFitness(populationFitnessList: List<Double>): Double

    abstract fun averageFitness(populationFitnessList: List<Double>): Double

}