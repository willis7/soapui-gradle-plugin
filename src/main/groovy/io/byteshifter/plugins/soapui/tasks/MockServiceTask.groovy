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
package io.byteshifter.plugins.soapui.tasks

import com.eviware.soapui.SoapUIProMockServiceRunner
import org.gradle.api.tasks.Optional

/**
 * Runs soapUI mockservice
 * task name - mock
 * @author Sion Williams
 */
class MockServiceTask extends SoapUITask {

    /**
     * The mockservice to run
     */
    @Optional
    String mockService

    /**
     * The path to listen on
     */
    @Optional
    private String path

    /**
     * The port to listen on
     */
    @Optional
    String port

    /**
     * To not wait for input
     */
    boolean noBlock

    /**
     * Tells Test Runner to skip tests.
     */
    @Optional
    boolean skip

    /**
     * Specified global property values
     */
    @Optional
    String[] globalProperties

    /**
     * Specified project property values
     */
    @Optional
    String[] projectProperties

    /**
     * Saves project file after running tests
     */
    boolean saveAfterRun

    /**
     * SoapUI Properties.
     */
    @Optional
    Properties soapuiProperties

    // pro
    @Optional String outputFolder
    @Optional boolean openReport

    MockServiceTask() {
        super('Runs soapUI mock service')
    }

    @Override
    void executeAction() {

        SoapUIProMockServiceRunner runner =
            new MySoapUIMockServiceRunner('soapUI Pro 5.1.2 Gradle MockService Runner')
        runner.projectFile = projectFile


        if (mockService) {
            runner.mockService = mockService
        }

        if (path) {
            runner.path = path
        }

        if (port) {
            runner.port = port
        }

        if (settingsFile) {
            runner.settingsFile = settingsFile
        }

        runner.block = !noBlock
        runner.saveAfterRun = saveAfterRun

        if (projectPassword) {
            runner.projectPassword = projectPassword
        }

        if (settingsPassword) {
            runner.soapUISettingsPassword = settingsPassword
        }

        if (globalProperties) {
            runner.globalProperties = globalProperties
        }

        if (projectProperties) {
            runner.projectProperties = projectProperties
        }

        if (soapuiProperties != null && soapuiProperties.size() > 0) {
            for (Object key : soapuiProperties.keySet()) {
                System.out.println('Setting ' + (String) key + ' value ' + soapuiProperties.getProperty((String) key))
                System.setProperty((String) key, soapuiProperties.getProperty((String) key))
            }
        }

        // pro
        if (getOutputFolder()) runner.outputFolder = getOutputFolder()
        logger.debug "outputFolder: ${getOutputFolder()}"
        if (isOpenReport()) runner.openReport = isOpenReport()
        logger.debug "openReport: ${isOpenReport()}"

        runner.run()
    }
}

/*
 * This class is a hack
 * see https://discuss.gradle.org/t/classpath-hell-soapui-and-gradle-api-logging-conflicts/8830/6?u=sion_williams
 */
class MySoapUIMockServiceRunner extends SoapUIProMockServiceRunner {
    MySoapUIMockServiceRunner() { super() }
    MySoapUIMockServiceRunner(String title) { super(title) }
    @Override void initGroovyLog() { }
}
