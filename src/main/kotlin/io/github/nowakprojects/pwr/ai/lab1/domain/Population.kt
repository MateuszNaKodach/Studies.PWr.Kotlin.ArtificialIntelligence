package io.github.nowakprojects.pwr.ai.lab1.domain

data class Population<T>(val chromosomes: List<Chromosome<T>>){
    val size: Int
        get() = chromosomes.size
}