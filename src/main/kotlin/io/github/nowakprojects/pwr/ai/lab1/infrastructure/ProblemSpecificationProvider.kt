package io.github.nowakprojects.pwr.ai.lab1.infrastructure

import io.github.nowakprojects.pwr.ai.lab1.domain.ProblemSpecification

interface ProblemSpecificationProvider {

    fun provide(): ProblemSpecification
}