package io.byteshifter.plugins.soapui.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUIMockServiceRunner
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

    MockServiceTask() {
        super('Runs soapUI mock service')
    }

    @Override
    public void executeAction() {

        SoapUIMockServiceRunner runner = new MySoapUIMockServiceRunner(
                'soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle MockService Runner')
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

        runner.run()
    }
}

public class MySoapUIMockServiceRunner extends SoapUIMockServiceRunner {
    public MySoapUIMockServiceRunner(){super()}
    public MySoapUIMockServiceRunner(String title){super(title)}

    @Override
    void initGroovyLog() {
    }
}