package io.github.nowakprojects.pwr.ai.lab1.domain

import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.Chromosome
import io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm.ChromosomeWithFitness

data class GeneticAlgorithmBestSolution<GENE, out FITNESS>(
        val chromosome: Chromosome<GENE>,
        val fitness: FITNESS,
        val epochs: Int,
        val executionTimeMillis: Long,
        val allSolutions: List<ChromosomeWithFitness<GENE>>
)