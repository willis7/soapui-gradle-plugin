package com.eviware.soapui.gradle

import com.eviware.soapui.gradle.extensions.SoapUIPluginExtension
import com.eviware.soapui.gradle.tasks.LoadTestTask
import com.eviware.soapui.gradle.tasks.MockServiceTask
import com.eviware.soapui.gradle.tasks.TestTask
import com.eviware.soapui.gradle.tasks.ToolTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * SoapUI implementation of the plugin interface
 *
 * @author Sion Williams
 */
class SoapUIPlugin implements Plugin<Project> {
    static final SOAP_TEST_TASK = 'test'
    static final TOOL_TASK = 'tool'
    static final SECURITY_TEST_TASK = 'securitytest'
    static final MOCK_TASK = 'mock'
    static final LOAD_TEST_TASK = 'loadtest'
    static final EXTENSION_NAME = 'soapui'

    @Override
    void apply(Project project) {
        // Create and install the extension object
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.create(EXTENSION_NAME, SoapUIPluginExtension)

        configSoapTest(project, soapUIPluginExtension)
        configTool(project, soapUIPluginExtension)
        configMock(project, soapUIPluginExtension)
        configLoad(project, soapUIPluginExtension)
    }

    /**
     * Configures soap test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSoapTest(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(SOAP_TEST_TASK, type: TestTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.test.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.test.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.test.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.test.settingsPassword }

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
    private void configTool(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(TOOL_TASK, type: ToolTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.tool.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.tool.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.tool.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.tool.settingsPassword }

            conventionMapping.iface = { soapUIPluginExtension.tool.iface }
            conventionMapping.tool = { soapUIPluginExtension.tool.tool }
            conventionMapping.outputFolder = { soapUIPluginExtension.tool.outputFolder }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.tool.soapuiProperties }
        }
    }

    /**
     * Configures mock task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configMock(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(MOCK_TASK, type: MockServiceTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.mock.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.mock.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.mock.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.mock.settingsPassword }

            conventionMapping.skip = { soapUIPluginExtension.mock.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.mock.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.mock.projectProperties }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.mock.saveAfterRun }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.mock.soapuiProperties }
        }
    }

    /**
     * Configures load task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configLoad(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(LOAD_TEST_TASK, type: LoadTestTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.load.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.load.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.load.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.load.settingsPassword }

            conventionMapping.testSuite = { soapUIPluginExtension.load.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.load.testCase }
            conventionMapping.username = { soapUIPluginExtension.load.username }
            conventionMapping.password = { soapUIPluginExtension.load.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.load.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.load.domain }
            conventionMapping.host = { soapUIPluginExtension.load.host }
            conventionMapping.endpoint = { soapUIPluginExtension.load.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.load.skip }
            conventionMapping.outputFolder = { soapUIPluginExtension.load.outputFolder }
            conventionMapping.printReport = { soapUIPluginExtension.load.printReport }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.load.saveAfterRun }
            conventionMapping.loadTest = { soapUIPluginExtension.load.loadTest }
            conventionMapping.limit = { soapUIPluginExtension.load.limit }
            conventionMapping.threadCount = { soapUIPluginExtension.load.threadCount }
        }
    }
}
