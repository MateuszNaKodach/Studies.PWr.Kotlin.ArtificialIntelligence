package io.github.nowakprojects.pwr.ai.lab1qap.domain

import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.ChromosomeWithFitness
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.GeneticAlgorithmSettings
import io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm.PopulationStats

data class GeneticAlgorithmSolution<GENE, out FITNESS>(
        val bestSolution: ChromosomeWithFitness<GENE>,
        val fullPopulationStatsList: List<PopulationStats<GENE>>,
        val passedEpochs: Int,
        val executionTimeMillis: Long,
        val selectionStrategyName: String,
        val settings: GeneticAlgorithmSettings
)