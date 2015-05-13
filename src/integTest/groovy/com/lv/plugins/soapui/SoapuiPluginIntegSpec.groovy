package com.lv.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import org.gradle.api.logging.LogLevel

/**
 * @author Sion Williams
 * @author Iain Adams
 */
class SoapuiPluginIntegSpec extends IntegrationSpec   {

    def setup() {
        copyResources('TemperatureConversions-soapui-project_PASS.xml', 'example-pass.xml')
        copyResources('TemperatureConversions-soapui-project_FAIL.xml', 'example-fail.xml')
    }

    def 'apply plugin, setup and run SoapUI test which passes'() {
        setup:
        buildFile << '''
            apply plugin: 'com.lv.soapui'

            soapui{
                test{
                    printReport = true
                    projectFile = "$projectDir/example-pass.xml"
                }
            }
        '''.stripIndent()
        fork = true

        when:
        ExecutionResult result = runTasksSuccessfully('soaptest')

        then:
        result.standardOutput.contains('Total TestCases: 4 (0 failed)')
    }

    def 'apply plugin, setup and run SoapUI test which fails'() {
        setup:
        buildFile << '''
            apply plugin: 'com.lv.soapui'

            soapui{
                test{
                    printReport = true
                    projectFile = "$projectDir/example-fail.xml"
                }
            }
        '''.stripIndent()
        fork = true

        when:
        ExecutionResult result = runTasksWithFailure('soaptest')

        then:
        result.standardOutput.contains('Total TestCases: 4 (1 failed)')
    }
}
