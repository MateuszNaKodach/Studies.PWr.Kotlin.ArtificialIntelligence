package io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1qap.extensions.takeRandom

class TournamentSelection<GENE>(
        private val tournamentParticipantsAmount: Int = 5,
        selectionGoal: SelectionGoal = SelectionGoal.MAXIMIZE_FITNESS,
        elitism: Boolean = true)
    : SelectionStrategy<GENE>(selectionGoal, elitism) {

    override fun selectChromosomeForNewPopulation(chromosomeWithNormalizedFitnessList: List<ChromosomeWithFitness<GENE>>): Chromosome<GENE> = findBestChromosomeOf(
            chromosomeWithNormalizedFitnessList
                    .takeRandom(tournamentParticipantsAmount)
    )

}