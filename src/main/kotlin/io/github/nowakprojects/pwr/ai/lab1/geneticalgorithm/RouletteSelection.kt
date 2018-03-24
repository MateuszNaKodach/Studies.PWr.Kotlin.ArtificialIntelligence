package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.extensions.takeRandom
import java.util.*

class RouletteSelection<GENE>(
        selectionGoal: SelectionGoal = SelectionGoal.MAXIMIZE_FITNESS,
        elitism: Boolean = true)
    : SelectionStrategy<GENE>(selectionGoal, elitism) {

    override fun selectChromosomeForNewPopulation(chromosomeWithNormalizedFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE> {
        val randomFitness = Random().nextDouble()
        var cumSum: Double = 0.0
        return chromosomeWithNormalizedFitnessList
                .takeWhile { cumSum < randomFitness }
                .onEach { cumSum += it.fitness }
                .last()
                .chromosome
    }
}