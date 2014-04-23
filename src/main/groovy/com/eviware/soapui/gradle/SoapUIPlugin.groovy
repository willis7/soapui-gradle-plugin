package com.eviware.soapui.gradle

import com.eviware.soapui.gradle.extensions.SoapUIPluginExtension
import com.eviware.soapui.gradle.tasks.*
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
    static final EXTENSION_NAME = 'soapui'

    @Override
    void apply( Project project ) {
        // Create and install the extension object
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.create(EXTENSION_NAME, SoapUIPluginExtension)

        configureParentTask( project, soapUIPluginExtension )
        configSoapTest( project, soapUIPluginExtension )
        configTool( project, soapUIPluginExtension )
    }

    /**
     * Configures parent task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configureParentTask ( Project project, SoapUIPluginExtension soapUIPluginExtension ) {
        project.tasks.withType( SoapUITask ).whenTaskAdded { SoapUITask task ->
            task.conventionMapping.projectFile = { soapUIPluginExtension.projectFile }
            task.conventionMapping.settingsFile = { soapUIPluginExtension.settingsFile }
            task.conventionMapping.projectPassword = { soapUIPluginExtension.projectPassword }
            task.conventionMapping.settingsPassword = { soapUIPluginExtension.settingsPassword }
        }
    }

    /**
     * Configures soap test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSoapTest( Project project, SoapUIPluginExtension soapUIPluginExtension   ) {
        project.task( SOAP_TEST_TASK, type: TestTask) {
            conventionMapping.testSuite = { soapUIPluginExtension.test.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.test.testCase }
            conventionMapping.username = { soapUIPluginExtension.test.username }
            conventionMapping.password = { soapUIPluginExtension.test.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.test.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.test.domain }
            conventionMapping.host = { soapUIPluginExtension.test.host }
            conventionMapping.endpoint = { soapUIPluginExtension.test.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.test.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.test.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.test.projectProperties }
            conventionMapping.outputFolder = { soapUIPluginExtension.test.outputFolder }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.test.soapuiProperties }
            conventionMapping.printReport = { soapUIPluginExtension.test.printReport }
            conventionMapping.interactive = { soapUIPluginExtension.test.interactive }
            conventionMapping.exportAll = { soapUIPluginExtension.test.exportAll }
            conventionMapping.junitReport = { soapUIPluginExtension.test.junitReport }
            conventionMapping.testFailIgnore = { soapUIPluginExtension.test.testFailIgnore }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.test.saveAfterRun }
        }
    }

    /**
     * Configures tool task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configTool( Project project, SoapUIPluginExtension soapUIPluginExtension   ) {
        project.task( TOOL_TASK, type: ToolTask) {
            conventionMapping.iface = { soapUIPluginExtension.tool.iface }
            conventionMapping.tool = { soapUIPluginExtension.tool.tool }
            conventionMapping.outputFolder = { soapUIPluginExtension.tool.outputFolder }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.tool.soapuiProperties }
        }
    }
}
