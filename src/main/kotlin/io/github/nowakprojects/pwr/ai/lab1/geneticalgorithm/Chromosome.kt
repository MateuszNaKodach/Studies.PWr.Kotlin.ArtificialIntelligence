package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

data class Chromosome<GENE>(val genes: List<GENE>) {
    val size
        get() = genes.size

    var fitness: Double? = null
}

