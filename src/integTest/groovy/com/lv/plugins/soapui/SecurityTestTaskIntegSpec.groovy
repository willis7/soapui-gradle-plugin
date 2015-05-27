package com.lv.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
/**
 * @author Iain Adams
 */
class SecurityTestTaskIntegSpec extends IntegrationSpec   {

    def 'run with no project.xml defined'() {
        setup:
        buildFile << """apply plugin: 'com.lv.soapui'"""

        when:
        ExecutionResult result = runTasksWithFailure('soapsecuritytest')

        then:
        result.standardError.contains("No value has been specified for property 'projectFile'.")
    }
}