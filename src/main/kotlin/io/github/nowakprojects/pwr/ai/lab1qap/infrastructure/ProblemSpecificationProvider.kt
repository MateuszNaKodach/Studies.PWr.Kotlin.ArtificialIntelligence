package io.github.nowakprojects.pwr.ai.lab1.infrastructure

import io.github.nowakprojects.pwr.ai.lab1.domain.FactoriesQapProblemSpecification

interface ProblemSpecificationProvider {

    fun provide(): FactoriesQapProblemSpecification
}