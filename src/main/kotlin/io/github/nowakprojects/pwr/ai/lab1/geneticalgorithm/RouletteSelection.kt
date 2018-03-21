package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.extensions.takeRandom
import java.util.*

class RouletteSelection<GENE>(
        selectionGoal: SelectionGoal,
        elitism: Boolean = true)
    : SelectionStrategy<GENE>(selectionGoal = SelectionGoal.MAXIMIZE_FITNESS, elitism = elitism) {

    override fun selectChromosomeForNewPopulation(chromosomeWithFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE> {
        val randomFitness = Random().nextDouble()
        var cumSum: Double = 0.0
        return chromosomeWithFitnessList
                .takeWhile { cumSum < randomFitness }
                .onEach { cumSum += it.fitness }
                .last()
                .chromosome
    }
}