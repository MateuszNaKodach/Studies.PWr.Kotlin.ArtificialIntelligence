package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.extensions.nextInt
import io.github.nowakprojects.pwr.ai.lab1.extensions.random
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.CrossoverStrategy
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.Population
import java.util.*

class FactoriesQapCrossover(
        private val possibleGenes: Array<Int>,
        crossoverProbability: Double
) : CrossoverStrategy<Int>(crossoverProbability) {

    //TODO: Fix crossover to takes only once every chromosome
    override fun crossoverPopulation(population: Population<Int>): Population<Int> {
        val random = Random()
        val chromosomesToCrossover = population.chromosomes.toMutableList()
        val crossoverChromosomes = mutableListOf<Chromosome<Int>>()
        val crossChromosomesCount = 2

        while(chromosomesToCrossover.size >= crossChromosomesCount){
            val crossoverCandidates = Pair(chromosomesToCrossover.removeAt(random.nextInt(1,chromosomesToCrossover.size,0)), chromosomesToCrossover.removeAt(0))
            if (toCrossover()) {
                crossoverChromosomes.addAll(crossover(crossoverCandidates.first, crossoverCandidates.second).toList())
            } else {
                crossoverChromosomes.addAll(crossoverCandidates.toList())
            }
        }
        return Population(crossoverChromosomes)
    }

    fun toCrossover() = Random().nextDouble() < crossoverProbability

    fun crossover(parent1: Chromosome<Int>, parent2: Chromosome<Int>): Pair<Chromosome<Int>, Chromosome<Int>> {
        val crossPoint = (parent1.size / 2)
        val child1 = Chromosome<Int>(listOf(parent1.genes.take(crossPoint), parent2.genes.drop(crossPoint)).flatten())
        val child2 = Chromosome<Int>(listOf(parent2.genes.take(crossPoint), parent1.genes.drop(crossPoint)).flatten())
        return Pair(replaceDuplicatesInChromosome(child1), replaceDuplicatesInChromosome(child2))
    }

    private fun replaceDuplicatesInChromosome(chromosome: Chromosome<Int>): Chromosome<Int> {
        val newGenesList = chromosome.genes.toMutableList()
        var duplicateIndex = findFirstDuplicateIndex(newGenesList)
        while (duplicateIndex != null) {
            val newGene = possibleGenes.filter { !newGenesList.contains(it) }.random()
            newGenesList[duplicateIndex] = newGene!!
            duplicateIndex = findFirstDuplicateIndex(newGenesList)
        }
        return Chromosome<Int>(newGenesList)
    }

    private fun findFirstDuplicateIndex(genes: List<Int>): Int? {
        (0 until genes.size).forEach {
            val gene = genes[it]
            if (genes.filterIndexed { index, _ -> index != it }.contains(gene)) {
                return it
            }
        }
        return null
    }

}