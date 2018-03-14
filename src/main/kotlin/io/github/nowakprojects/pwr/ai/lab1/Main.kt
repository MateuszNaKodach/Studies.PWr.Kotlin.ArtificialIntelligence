package io.github.nowakprojects.pwr.ai.lab1

import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ResourcesFileProblemSpecificationProvider


fun main(args: Array<String>) {
    val problemSpecification = ResourcesFileProblemSpecificationProvider().provide()

    problemSpecification.distanceMatrix.prettyPrint()
}
