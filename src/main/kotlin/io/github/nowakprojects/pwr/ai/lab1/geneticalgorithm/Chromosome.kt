package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

data class Chromosome<GENE>(val genes: List<GENE>) {
    val size
        get() = genes.size

    /**
     * Break of OOP, only for better algorithm performance.
     */
    var fitness: Double? = null
}

