package io.github.nowakprojects.pwr.ai.lab1

import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ProblemSpecificationNotFound
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ProblemSpecificationProvider
import io.github.nowakprojects.pwr.ai.lab1.infrastructure.ResourcesFileProblemSpecificationProvider
import spock.lang.Specification

class FileProblemSpecificationProviderSpec extends Specification {


    def "Opening existing file #fileName with data for laboratory exercise"() {
        given: "File path with #fileName"
        def filePath = "/lab1/" + fileName
        ProblemSpecificationProvider specificationProvider = new ResourcesFileProblemSpecificationProvider(filePath)

        when: "Load data from file"
        specificationProvider.provide()

        then: "data is loaded"
        notThrown(ProblemSpecificationNotFound)

        where:
        fileName    | _
        "had12.dat" | _
        "had14.dat" | _
        "had16.dat" | _
        "had18.dat" | _
        "had20.dat" | _
    }

    def "Opening not existing file #fileName for loading exercise data"() {
        given: "File path with #fileName"
        def filePath = "/lab1/" + fileName
        ProblemSpecificationProvider specificationProvider = new ResourcesFileProblemSpecificationProvider(filePath)

        when: "Load data from file"
        specificationProvider.provide()

        then: "data is loaded"
        ProblemSpecificationNotFound exception = thrown()

        where:
        fileName                   | _
        "had33.dat"                | _
        "MateuszNowak.dat"         | _
        "PWr.dat"                  | _
        "had19.dat"                | _
        "SztucznaInteligencja.dat" | _
    }
}
