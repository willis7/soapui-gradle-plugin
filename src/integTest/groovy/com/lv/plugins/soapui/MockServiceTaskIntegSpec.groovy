package com.lv.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import spock.lang.Ignore

/**
 * @author Iain Adams
 */
class MockServiceTaskIntegSpec extends IntegrationSpec   {

    def 'run with no project.xml defined'() {
        setup:
        buildFile << """apply plugin: 'com.lv.soapui'"""

        when:
        ExecutionResult result = runTasksWithFailure('soapmock')

        then:
        result.standardError.contains("No value has been specified for property 'projectFile'.")
    }

    @Ignore
    //TODO How do i do a keypress to stop the mock service?
    def 'run a test mock service project'() {
        setup:
        copyResources('test-mock-service-soapui-project.xml', 'test-mock-service-soapui-project.xml')
        buildFile << """apply plugin: 'com.lv.soapui'

        soapui {
            mock {
                projectFile = "$projectDir/test-mock-service-soapui-project.xml"
            }
        }
        """.stripIndent()
        fork = true

        when:
        ExecutionResult result = runTasksSuccessfully('soapmock')

        then:
        result.standardOutput.contains("No value has been specified for property 'projectFile'.")
    }
}
