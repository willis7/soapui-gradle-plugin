/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Sion Williams
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.byteshifter.plugins.soapui

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

/**
 * @author Sion Williams
 * @author Iain Adams
 */
class SoapuiPluginIntegSpec extends IntegrationSpec {

    def setup() {
        copyResources('TemperatureConversions-soapui-project_PASS.xml', 'example-pass.xml')
        copyResources('TemperatureConversions-soapui-project_FAIL.xml', 'example-fail.xml')
    }

    def 'apply plugin, setup and run SoapUI test which passes'() {
        setup:
        buildFile << '''
            apply plugin: 'io.byteshifter.soapui'

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
            apply plugin: 'io.byteshifter.soapui'

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
