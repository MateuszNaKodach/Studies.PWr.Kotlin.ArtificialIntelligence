package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.extensions.takeRandom

class TournamentSelection<GENE>(
        private val tournamentParticipantsAmount: Int = 5,
        selectionGoal: SelectionGoal = SelectionGoal.MAXIMIZE_FITNESS,
        elitism: Boolean = true)
    : SelectionStrategy<GENE>(selectionGoal, elitism) {

    override fun selectChromosomeForNewPopulation(chromosomeWithFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE>
        = findBestChromosomeOf(
            chromosomeWithFitnessList
                    .takeRandom(tournamentParticipantsAmount)
        )

}