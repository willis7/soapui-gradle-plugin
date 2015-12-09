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

import io.byteshifter.plugins.soapui.extensions.SoapUILoadConvention
import io.byteshifter.plugins.soapui.extensions.SoapUIMockConvention
import io.byteshifter.plugins.soapui.extensions.SoapUIPluginExtension
import io.byteshifter.plugins.soapui.extensions.SoapUISecurityConvention
import io.byteshifter.plugins.soapui.extensions.SoapUITestConvention
import io.byteshifter.plugins.soapui.extensions.SoapUIToolConvention
import io.byteshifter.plugins.soapui.tasks.LoadTestTask
import io.byteshifter.plugins.soapui.tasks.MockServiceTask
import io.byteshifter.plugins.soapui.tasks.SecurityTestTask
import io.byteshifter.plugins.soapui.tasks.TestTask
import io.byteshifter.plugins.soapui.tasks.ToolTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * SoapUI implementation of the plugin interface
 *
 * @author Sion Williams
 */
class SoapUIPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Create and install the extension object
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.create( 'soapui', SoapUIPluginExtension )
        soapUIPluginExtension.extensions.create( 'tool', SoapUIToolConvention )
        soapUIPluginExtension.extensions.create( 'security', SoapUISecurityConvention )
        soapUIPluginExtension.extensions.create( 'load', SoapUILoadConvention )
        soapUIPluginExtension.extensions.create( 'test', SoapUITestConvention )
        soapUIPluginExtension.extensions.create( 'mock', SoapUIMockConvention )

        configSoapTest( project, soapUIPluginExtension )
        configSoapTool( project, soapUIPluginExtension )
        configSoapMock( project, soapUIPluginExtension )
        configSoapLoad( project, soapUIPluginExtension )
        configSoapSecurityTest( project, soapUIPluginExtension )
    }

    /**
     * Configures soap test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSoapTest(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task( 'soaptest', type: TestTask) {
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
    private void configSoapTool(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task( 'soaptool', type: ToolTask ) {
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
    private void configSoapMock(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task( 'soapmock', type: MockServiceTask ) {
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
    private void configSoapLoad(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task( 'soaploadtest', type: LoadTestTask ) {
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

    /**
     * Configures soap security test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSoapSecurityTest(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task( 'soapsecuritytest', type: SecurityTestTask ) {
            conventionMapping.projectFile = { soapUIPluginExtension.security.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.security.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.security.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.security.settingsPassword }

            conventionMapping.securityTest = { soapUIPluginExtension.security.securityTest }
            conventionMapping.testSuite = { soapUIPluginExtension.security.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.security.testCase }
            conventionMapping.username = { soapUIPluginExtension.security.username }
            conventionMapping.password = { soapUIPluginExtension.security.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.security.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.security.domain }
            conventionMapping.host = { soapUIPluginExtension.security.host }
            conventionMapping.endpoint = { soapUIPluginExtension.security.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.security.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.security.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.security.projectProperties }
            conventionMapping.outputFolder = { soapUIPluginExtension.security.outputFolder }
            conventionMapping.printReport = { soapUIPluginExtension.security.printReport }
            conventionMapping.interactive = { soapUIPluginExtension.security.interactive }
            conventionMapping.exportAll = { soapUIPluginExtension.security.exportAll }
            conventionMapping.junitReport = { soapUIPluginExtension.security.junitReport }
            conventionMapping.testFailIgnore = { soapUIPluginExtension.security.testFailIgnore }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.security.saveAfterRun }
        }
    }
}
