package com.eviware.soapui.gradle

import com.eviware.soapui.gradle.tasks.TestTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * SoapUI implementation of the plugin interface
 *
 * @author Sion
 */
class SoapUIPlugin implements Plugin < Project > {
    static final SOAP_TEST_TASK = 'soapTest'
    static final TOOL_TASK = 'tool'
    static final SECURITY_TEST_TASK = 'securityTest'
    static final MOCK_TASK = 'mock'
    static final LOAD_TEST_TASK = 'loadTest'

    @Override
    void apply( Project project ) {
        configSoapTest( project )
    }

    private void configSoapTest( Project project ) {
        project.task( SOAP_TEST_TASK, type: TestTask ) {
            group = 'SoapUI'
            description = 'Runs soapUI functional tests'

            projectFile = 'sample-soapui-project.xml'
            printReport = true
            exportAll = true
            junitReport = true
            interactive = false
            testFailIgnore = true
            saveAfterRun = false
        }
    }
}
