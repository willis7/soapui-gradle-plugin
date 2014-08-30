package com.lv.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import org.gradle.api.logging.LogLevel

/**
 * @author Sion Williams
 */
class SoapuiPluginIntegSpec extends IntegrationSpec   {

    def 'apply plugin, setup and run SoapUI test'() {
        buildFile << '''
            apply plugin: 'com.lv.soapui'

            soapui{
                test{
                    projectFile = 'sample-soapui-project.xml'
                }
            }
        '''.stripIndent()

        when:
        ExecutionResult result = runTasksSuccessfully('soaptest')

        then:
        result.standardOutput.contains(':soaptest')
    }
}
