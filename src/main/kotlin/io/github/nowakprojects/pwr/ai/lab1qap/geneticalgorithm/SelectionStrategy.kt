package io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm

abstract class SelectionStrategy<GENE>(
        private val selectionGoal: SelectionGoal = SelectionGoal.MINIMIZE_FITNESS,
        protected val elitism: Boolean = false
) {
    fun selectNewPopulation(population: Population<GENE>, populationFitnessList: List<Double>): Population<GENE> {
        val normalizedFitnessList = normaliseFitnessForSelection(populationFitnessList)
        val chromosomeWithNormalizedFitnessForSelectionList =
                (0 until population.size).map { ChromosomeWithFitness<GENE>(population.chromosomes[it], normalizedFitnessList[it]) }
        return PopulationSelector(chromosomeWithNormalizedFitnessForSelectionList).makeSelection()
    }

    protected abstract fun selectChromosomeForNewPopulation(chromosomeWithNormalizedFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE>

    //FIXME: Write it better, pass FitnessComparator, which takes two chromosomes
    fun findBestChromosomeOf(chromosomeWithFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE> = when (selectionGoal) {
        SelectionGoal.MINIMIZE_FITNESS -> chromosomeWithFitnessList.minBy { it.fitness }!!.chromosome
        SelectionGoal.MAXIMIZE_FITNESS -> chromosomeWithFitnessList.maxBy { it.fitness }!!.chromosome
    }

    private fun normaliseFitnessForSelection(fitnessList: List<Double>): List<Double> {
        val fitnessListSum = fitnessList.sum()
        return fitnessList
                .map { it / fitnessListSum }
    }


    private inner class PopulationSelector(
            private val chromosomeWithFitnessList: List<ChromosomeWithFitness<GENE>>
    ) {
        fun makeSelection(): Population<GENE> {
            val newPopulationChromosomes: MutableList<Chromosome<GENE>> = mutableListOf()
            var newPopulationSize = chromosomeWithFitnessList.size

            if (elitism) {
                newPopulationChromosomes.add(findBestChromosome())
                newPopulationSize -= 1
            }

            (1..newPopulationSize)
                    .forEach { newPopulationChromosomes.add(selectChromosomeForNewPopulation(chromosomeWithFitnessList)) }
            return Population(newPopulationChromosomes)
        }

        private fun findBestChromosome() = findBestChromosomeOf(chromosomeWithFitnessList)
    }

    enum class SelectionGoal {
        MAXIMIZE_FITNESS,
        MINIMIZE_FITNESS;
    }
}