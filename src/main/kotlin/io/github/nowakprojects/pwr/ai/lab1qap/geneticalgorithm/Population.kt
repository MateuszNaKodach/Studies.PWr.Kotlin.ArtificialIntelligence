package io.github.nowakprojects.pwr.ai.lab1qap.geneticalgorithm

data class Population<T>(val chromosomes: List<Chromosome<T>>){
    val size: Int
        get() = chromosomes.size

    fun get(index: Int) = chromosomes[index]
}