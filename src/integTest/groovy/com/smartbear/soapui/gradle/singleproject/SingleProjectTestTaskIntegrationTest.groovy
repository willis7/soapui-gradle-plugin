package com.smartbear.soapui.gradle.singleproject

import org.gradle.tooling.model.GradleProject

/**
 * Single project test task integration test.
 *
 * @author Sion Williams
 */
class SingleProjectTestTaskIntegrationTest extends SingleProjectBuildIntegrationTest {
/*
TODO: implement integration tests

    def "Configures the SoapUI plugin and runs the soaptest"() {
        when: "I add the build script and run the task"
        buildFile << """
version = '1.0'
group = 'org.gradle.mygroup'

soapui {
    test {
        projectFile = 'sample-soapui-project.xml'
        outputDirectory = "$buildDir/soapui/output"
        printReport = true
        exportAll = true
        junitReport = true
        interactive = false
        testFailIgnore = true
        saveAfterRun = false
    }
}

"""
        GradleProject project = runTasks(integTestDir, 'soaptest')

        then: "I should get an output file"
        true

    }
*/
}
