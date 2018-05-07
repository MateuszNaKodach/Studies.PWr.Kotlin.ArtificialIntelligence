package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.ChromosomeWithFitness
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.GeneticAlgorithmSettings
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.PopulationStats

/*
data class GeneticAlgorithmSolution<GENE, out FITNESS>(
        val chromosome: Chromosome<GENE>,
        val fitness: FITNESS,
        val epochs: Int,
        val executionTimeMillis: Long,
        val allSolutions: List<ChromosomeWithFitness<GENE>>
)*/


data class GeneticAlgorithmSolution<GENE, out FITNESS>(
        val bestSolution: ChromosomeWithFitness<GENE>,
        val fullPopulationStatsList: List<PopulationStats<GENE>>,
        val passedEpochs: Int,
        val executionTimeMillis: Long,
        val selectionStrategyName: String,
        val settings: GeneticAlgorithmSettings
)