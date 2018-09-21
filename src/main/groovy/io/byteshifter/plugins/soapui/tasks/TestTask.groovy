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

import com.eviware.soapui.SoapUIProTestCaseRunner
import org.gradle.api.tasks.Optional

/**
 * Runs soapUI functional tests
 * task name - soaptest
 * @author Sion Williams
 */
class TestTask extends SoapUITask {
    /**
     * The TestSuite to run project file to test with
     */
    @Optional
    String testSuite

    /**
     * The TestCase to run project file to test with
     */
    @Optional
    String testCase

    /**
     * The username to use for authentication challenges
     */
    @Optional
    String username

    /**
     * The password to use for authentication challenges
     */
    @Optional
    String password

    /**
     * The WSS password-type to use for any authentications. Setting this will
     * result in the addition of WS-Security UsernamePassword tokens to any
     * outgoing request containing the specified username and password. Set to
     * either 'Text' or 'Digest'
     */
    @Optional
    String wssPasswordType

    /**
     * The domain to use for authentication challenges
     */
    @Optional
    String domain

    /**
     * The host to use for requests
     */
    @Optional
    String host

    /**
     * Overrides the endpoint to use for requests
     */
    @Optional
    String endpoint

    /**
     * Sets the output folder for reports
     */
    @Optional
    String outputFolder

    /**
     * Turns on printing of reports
     */
    boolean printReport

    /**
     * Enabled interactive groovy scripts
     */
    boolean interactive

    /**
     * Turns on exporting of all results
     */
    boolean exportAll

    /**
     * Turns on creation of reports in junit style
     */
    boolean junitReport = false

    /**
     * Tells Test Runner to skip tests.
     */
    @Optional
    boolean skip

    /**
     * If set ignore failed tests
     */
    boolean testFailIgnore

    /**
     * Specified global property values soapui.saveAfterRun
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
    Properties soapuiProperties

    // pro
    @Optional String environment
    @Optional String reportName
    @Optional String[] reportFormats
    @Optional boolean openReport

    TestTask() {
        super('Runs soapUI functional tests')
    }


    @Override
    void executeAction() {
        SoapUIProTestCaseRunner runner = new MySoapUITestCaseRunner('soapUI Pro 5.1.2 Gradle TestCase Runner')
        runner.setProjectFile( getProjectFile() )

        if (getEndpoint()) {
            runner.endpoint = getEndpoint()
            logger.debug "Runner endpoint: " + getEndpoint()
        }

        if (getTestSuite()) {
            runner.testSuite = getTestSuite()
            logger.debug "Runner testSuite: " + getTestSuite()
        }

        if (getTestCase()) {
            runner.testCase = getTestCase()
            logger.debug "Runner testCase: " + getTestCase()
        }

        if (getUsername()) {
            runner.username = getUsername()
            logger.debug "Runner username: " + getUsername()
        }

        if (getPassword()) {
            runner.password = getPassword()
            logger.debug "Runner password: " + getPassword()
        }

        if (getWssPasswordType()) {
            runner.wssPasswordType = getWssPasswordType()
            logger.debug "Runner wssPasswordType: " + getWssPasswordType()
        }

        if (getDomain()) {
            runner.domain = getDomain()
            logger.debug "Runner domain: " + getDomain()
        }

        if (getHost()) {
            runner.host = getHost()
            logger.debug "Runner host: " + getHost()
        }

        if (getOutputFolder()) {
            runner.outputFolder = getOutputFolder()
            logger.debug "Runner outputFolder: " + getOutputFolder()
        }

        runner.printReport = getPrintReport()
        logger.debug "Runner printReport: " + getPrintReport()

        runner.exportAll = getExportAll()
        logger.debug "Runner exportAll: " + getExportAll()

        runner.setJUnitReport(getJunitReport())
        logger.debug "Runner junitReport: " + getJunitReport()

        runner.enableUI = getInteractive()
        logger.debug "Runner enableUI: " + getInteractive()

        runner.ignoreError = getTestFailIgnore()
        logger.debug "Runner ignoreError: " + getTestFailIgnore()

        runner.saveAfterRun = getSaveAfterRun()
        logger.debug "Runner saveAfterRun: " + getSaveAfterRun()

        if (getSettingsFile()) {
            runner.settingsFile = getSettingsFile()
            logger.debug "Runner settingsFile: " + getSettingsFile()
        }

        if (getProjectPassword()) {
            runner.projectPassword = getProjectPassword()
            logger.debug "Runner projectPassword: " + getProjectPassword()
        }

        if (getSettingsPassword()) {
            runner.soapUISettingsPassword = getSettingsPassword()
            logger.debug "Runner soapUISettingsPassword: " + getSettingsPassword()
        }

        if (getGlobalProperties()) {
            runner.globalProperties = getGlobalProperties()
            logger.debug "Runner getGlobalProperties: " + getGlobalProperties()
        }

        if (getProjectProperties()) {
            runner.projectProperties = getProjectProperties()
            logger.debug "Runner projectProperties: " + getProjectProperties()
        }

        if (getSoapuiProperties() && getSoapuiProperties().size() > 0 ) {
            getSoapuiProperties().keySet().each { key ->
                logger.debug "Setting ${key} value ${getSoapuiProperties().getProperty("${key}")}"
                System.setProperty((String) key, getSoapuiProperties().getProperty((String) key))
            }
        }

        // pro
        if (getEnvironment()) runner.environment = getEnvironment()
        logger.debug "environment: ${getEnvironment()}"
        if (getReportName()) runner.reportName = getReportName() ?: 'defaultReportName'
        logger.debug "reportName: ${getReportName()}"
        if (getReportFormats()) runner.reportFormats = getReportFormats() ?: ['TXT']
        logger.debug "reportFormats: ${getReportFormats()}"
        if (isOpenReport()) runner.openReport = isOpenReport()
        logger.debug "openReport: ${isOpenReport()}"

        runner.run()
        logger.info "SoapUI test case runner complete."
    }
}

/*
 * This class is a hack, see https://discuss.gradle.org/t/classpath-hell-soapui-and-gradle-api-logging-conflicts/8830/6?u=sion_williams
 */
class MySoapUITestCaseRunner extends SoapUIProTestCaseRunner {
    MySoapUITestCaseRunner() { super() }
    MySoapUITestCaseRunner(String title) { super(title) }
    @Override void initGroovyLog() { }
}
