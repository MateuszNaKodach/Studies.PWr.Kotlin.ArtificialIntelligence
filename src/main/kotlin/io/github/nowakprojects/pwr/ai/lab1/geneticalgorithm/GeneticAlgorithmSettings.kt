package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

data class GeneticAlgorithmSettings(
        val epochLimit: Int,
        val populationSize: Int,
        val crossoverProbability: Double,
        val mutationProbability: Double
) {
}