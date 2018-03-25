package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import java.util.*

abstract class MutationStrategy<GENE>(private val mutationProbability: Double) {

    fun mutatePopulation(population: Population<GENE>): Population<GENE> =
            Population(
                    population.chromosomes
                            .map { if (toMutate()) mutate(it) else it.copy() }
            )


    protected abstract fun mutate(chromosome: Chromosome<GENE>): Chromosome<GENE>

    private fun toMutate(): Boolean = Random().nextDouble() < mutationProbability
}