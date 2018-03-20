package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.AbstractGeneticAlgorithm
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.PopulationCreator
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.TournamentSelection

class FactoriesQapGeneticAlgorithm(
        private val factoriesQapProblemSpecification: FactoriesQapProblemSpecification,
        epochLimit: Int,
        populationSize: Int,
        crossoverProbability: Double,
        mutationProbability: Double
) : AbstractGeneticAlgorithm<Int>(
        epochLimit,
        populationSize,
        crossoverProbability,
        mutationProbability,
        PopulationCreator(factoriesQapProblemSpecification.getPossibleFactories(), populationSize),
        TournamentSelection<Int>(),
        FactoriesQapCrossover(factoriesQapProblemSpecification.getPossibleFactories(), crossoverProbability),
        FactoriesQapMutation(mutationProbability)
) {

    override fun computeFitness(chromosome: Chromosome<Int>): Double {
        factoriesQapProblemSpecification.flowMatrix
    }

    override fun bestFitness(populationFitnessList: List<Double>) = populationFitnessList.min()

}