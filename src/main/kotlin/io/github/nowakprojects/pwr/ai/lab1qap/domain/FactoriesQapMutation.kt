package io.github.nowakprojects.pwr.ai.lab1qap.domain

import io.github.nowakprojects.pwr.ai.lab1qap.extensions.nextInt
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.MutationStrategy
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.Population
import java.util.*

class FactoriesQapMutation(mutationProbability: Double) : MutationStrategy<Int>(mutationProbability) {

    override fun mutate(chromosome: Chromosome<Int>): Chromosome<Int> {
        val mutatedGenes = chromosome.genes.toMutableList()
        val random = Random()
        val firstToSwapIndex = random.nextInt(mutatedGenes.size)
        val secondToSwapIndex = random.nextInt(max = mutatedGenes.size, except = firstToSwapIndex)
        mutatedGenes[firstToSwapIndex] = mutatedGenes[secondToSwapIndex].also { mutatedGenes[secondToSwapIndex] = mutatedGenes[firstToSwapIndex] }
        return Chromosome<Int>(mutatedGenes)
    }
}