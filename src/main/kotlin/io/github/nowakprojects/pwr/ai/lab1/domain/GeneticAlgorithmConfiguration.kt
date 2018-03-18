package io.github.nowakprojects.pwr.ai.lab1.domain

abstract class GeneticAlgorithmConfiguration<GENE>(
        val populationSize : Int,
        val crossoverProbability: Double,
        val mutationProbability: Double,
        val populationCreator: PopulationCreator<GENE>
){

    fun execute(){

    }


}