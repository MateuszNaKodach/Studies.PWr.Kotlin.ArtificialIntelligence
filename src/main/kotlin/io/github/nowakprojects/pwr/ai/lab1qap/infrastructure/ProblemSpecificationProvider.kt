package io.github.nowakprojects.pwr.ai.lab1qap.infrastructure

import io.github.nowakprojects.pwr.ai.lab1qap.domain.FactoriesQapProblemSpecification

interface ProblemSpecificationProvider {

    fun provide(): FactoriesQapProblemSpecification
}