package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.extensions.takeRandom
import java.util.*

class RouletteSelection<GENE>(
        selectionGoal: SelectionGoal = SelectionGoal.MAXIMIZE_FITNESS,
        elitism: Boolean = true)
    : SelectionStrategy<GENE>(selectionGoal, elitism) {
//TODO: Odjąć minimum od ruletki w normalizacji!
    override fun selectChromosomeForNewPopulation(chromosomeWithNormalizedFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE> {
        val randomFitness = Random().nextDouble()
        var cumSum: Double = 0.0
        var i = 0
        while(cumSum < randomFitness && i <chromosomeWithNormalizedFitnessList.size-1){
            cumSum += chromosomeWithNormalizedFitnessList[i].fitness
            i++
        }
        return chromosomeWithNormalizedFitnessList[i].chromosome
    }
}