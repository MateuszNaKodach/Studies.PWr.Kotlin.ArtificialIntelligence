package io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain

import io.github.nowakprojects.pwr.ai.lab2csp.csp.CSPSolution

class NoAttackedQueenPlaces(selectedPlaces: List<QueenPlace>) : CSPSolution<Row,Column,QueenPlace>(selectedPlaces) {
}