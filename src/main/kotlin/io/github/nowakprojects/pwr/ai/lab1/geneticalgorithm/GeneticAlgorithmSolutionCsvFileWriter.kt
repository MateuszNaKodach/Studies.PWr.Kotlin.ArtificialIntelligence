package io.github.nowakprojects.pwr.ai.lab1.geneticalgorithm

import io.github.nowakprojects.pwr.ai.lab1.domain.GeneticAlgorithmSolution
import java.io.File
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GeneticAlgorithmSolutionCsvFileWriter<GENE, in FITNESS> {

    fun writeToCsv(geneticAlgorithmSolution: GeneticAlgorithmSolution<GENE, FITNESS>, filename: String = generateFilenameFor(geneticAlgorithmSolution)) {
        val csvFile = File(generateFilenameFor(geneticAlgorithmSolution))
        csvFile.createNewFile()
        val printWriter = PrintWriter(csvFile)
        printWriter.println(generateFileHeaderFor(geneticAlgorithmSolution.fullPopulationStatsList.first()))
        geneticAlgorithmSolution.fullPopulationStatsList.forEachIndexed { index, it -> printWriter.println(generateCsvRowFor(index+1, it)) }
        printWriter.close()
    }

    private fun generateFilenameFor(geneticAlgorithmSolution: GeneticAlgorithmSolution<GENE, FITNESS>): String {
        val selectionStrategyName = geneticAlgorithmSolution.selectionStrategyName
        val (epochLimit, populationSize, crossoverProbability, mutationProbability) = geneticAlgorithmSolution.settings
        return "${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))}-QAP-PS$populationSize-EP$epochLimit-CP$crossoverProbability-MP$mutationProbability-$selectionStrategyName.csv"
    }

    private fun generateFileHeaderFor(populationStats: PopulationStats<GENE>): String = "epoch  bestFitness  averageFitness   worstFitness"

    private fun generateCsvRowFor(epochIndex: Int, populationStats: PopulationStats<GENE>): String = "$epochIndex   ${"%.2f".format(populationStats.bestFitness)}  ${"%.2f".format(populationStats.averageFitness)}   ${"%.2f".format(populationStats.worstFitness)}"

}