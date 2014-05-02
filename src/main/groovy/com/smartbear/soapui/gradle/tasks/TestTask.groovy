package com.smartbear.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUITestCaseRunner
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

    TestTask() {
        super('Runs soapUI functional tests')
    }


    @Override
    void executeAction() {

        SoapUITestCaseRunner runner = new SoapUITestCaseRunner(
                'soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle TestCase Runner')
        runner.projectFile = getProjectFile()

        if (getEndpoint()) {
            runner.endpoint = getEndpoint()
        }

        if (getTestSuite()) {
            runner.testSuite = getTestSuite()
        }

        if (getTestCase()) {
            runner.testCase = getTestCase()
        }

        if (getUsername()) {
            runner.username = getUsername()
        }

        if (getPassword()) {
            runner.password = getPassword()
        }

        if (getWssPasswordType()) {
            runner.wssPasswordType = getWssPasswordType()
        }

        if (getDomain()) {
            runner.domain = getDomain()
        }

        if (getHost()) {
            runner.host = getHost()
        }

        if (getOutputFolder()) {
            runner.outputFolder = getOutputFolder()
        }

        runner.printReport = getPrintReport()
        runner.exportAll = getExportAll()
        runner.junitReport = getJunitReport()
        runner.enableUI = getInteractive()
        runner.ignoreError = getTestFailIgnore()
        runner.saveAfterRun = getSaveAfterRun()

        if (getSettingsFile()) {
            runner.settingsFile = getSettingsFile()
        }

        if (getProjectPassword()) {
            runner.projectPassword = getProjectPassword()
        }

        if (getSettingsPassword()) {
            runner.soapUISettingsPassword = getSettingsPassword()
        }

        if (getGlobalProperties()) {
            runner.globalProperties = getGlobalProperties()
        }

        if (getProjectProperties()) {
            runner.projectProperties = getProjectProperties()
        }

        if (soapuiProperties && soapuiProperties.size() > 0) {
            for (Object key : soapuiProperties.keySet()) {
                println('Setting ' + (String) key + ' value ' + soapuiProperties.getProperty((String) key))
                System.setProperty((String) key, soapuiProperties.getProperty((String) key))
            }
        }

        runner.run()
    }
}
