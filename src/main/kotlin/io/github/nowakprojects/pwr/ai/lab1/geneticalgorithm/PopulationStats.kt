package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm


data class PopulationStats<GENE>(
        val bestChromosomeWithFitness: ChromosomeWithFitness<GENE>,
        val averageFitness: Double,
        val worstChromosomeWithFitness: ChromosomeWithFitness<GENE>
) {

    val bestChromosome
        get() = bestChromosomeWithFitness.chromosome

    val bestFitness
        get() = bestChromosomeWithFitness.fitness

    val worstChromosome
        get() = worstChromosomeWithFitness.chromosome

    val worstFitness
        get() = worstChromosomeWithFitness.fitness

    override fun toString(): String =
            " - Best fitness: $bestFitness for $bestChromosome \n - Average fitness $averageFitness \n - Worst fitness: $worstFitness"
}