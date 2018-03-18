package io.github.nowakprojects.pwr.ai.lab1.domain

class PopulationCreator<GENE>(
        private val possibleGenes: Array<GENE>,
        private val chromosomesAmount: Int
) {
    fun createRandomPopulation() =
            createWithChromosomes(
                    (0..chromosomesAmount)
                            .map { Chromosome<GENE>(possibleGenes.toList().shuffled()) }
            )

    fun createWithChromosomes(chromosomes: List<Chromosome<GENE>>) =
            Population<GENE>(chromosomes)

    fun createChromosome(genes: List<GENE>): Chromosome<GENE> = Chromosome<GENE>(genes)
}