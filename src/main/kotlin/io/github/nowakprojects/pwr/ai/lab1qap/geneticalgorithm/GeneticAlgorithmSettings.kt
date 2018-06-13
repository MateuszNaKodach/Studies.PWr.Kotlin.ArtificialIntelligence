package io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm

data class GeneticAlgorithmSettings(
        val epochLimit: Int,
        val populationSize: Int,
        val crossoverProbability: Double,
        val mutationProbability: Double
) {
}