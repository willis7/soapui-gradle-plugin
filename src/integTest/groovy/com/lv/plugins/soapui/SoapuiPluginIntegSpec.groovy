package com.lv.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import org.gradle.api.logging.LogLevel

/**
 * @author Sion Williams
 */
class SoapuiPluginIntegSpec extends IntegrationSpec   {

    def setup() {
        copyResources('Amazon-soapui-project.xml', 'Amazon-soapui-project.xml')
    }

    def 'apply plugin, setup and run SoapUI test'() {
        setup:
        buildFile << '''
            apply plugin: 'com.lv.soapui'

            soapui{
                test{
                    projectFile = 'Amazon-soapui-project.xml'
                }
            }
        '''.stripIndent()
        //logLevel = LogLevel.DEBUG

        when:
        ExecutionResult result = runTasksSuccessfully('soaptest')

        then:
        result.standardOutput.contains(':soaptest')
    }
}
