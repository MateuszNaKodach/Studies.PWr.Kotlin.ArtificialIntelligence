package io.github.nowakprojects.pwr.ai.lab1.domain

abstract class SelectionStrategy<GENE>(
        protected val elitism: Boolean = true
) {
    fun selectNewPopulation(population: Population<GENE>, populationFitnessList: List<Double>): Population<GENE> {
        return PopulationSelector(population.chromosomes, populationFitnessList).makeSelection()
    }

    abstract fun selectChromosomeForNewPopulation(chromosomes: List<Chromosome<GENE>>, populationFitnessList: List<Double>): Chromosome<GENE>

    protected open fun findBestChromosomeOf(chromosomes: List<Chromosome<GENE>>, populationFitnessList: List<Double>): Chromosome<GENE> = chromosomes[findBestChromosomeIndex(populationFitnessList)]

    private fun findBestChromosomeIndex(populationFitnessList: List<Double>) = populationFitnessList.indexOf(populationFitnessList.max())


    inner class PopulationSelector(
            val populationChromosomes: List<Chromosome<GENE>>,
            val populationFitnessList: List<Double>
    ) {
        fun makeSelection(): Population<GENE> {
            val newPopulationChromosomes: MutableList<Chromosome<GENE>> = mutableListOf()
            var newPopulationSize = populationChromosomes.size

            if (elitism) {
                newPopulationChromosomes.add(findBestChromosome())
                newPopulationSize -= 1
            }

            (0..newPopulationSize)
                    .forEach { newPopulationChromosomes.add(selectChromosomeForNewPopulation(populationChromosomes, populationFitnessList)) }
            return Population(newPopulationChromosomes)
        }

        fun findBestChromosome() = findBestChromosomeOf(populationChromosomes, populationFitnessList);
    }
}