package com.smartbear.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUIToolRunner
import org.gradle.api.tasks.Optional

/**
 * Runs soapUI tools
 * task name - tool
 *
 * @author Sion
 */
class ToolTask extends SoapUITask {

    /**
     * The tool to run
     */
    @Optional
    String tool

    /**
     * The interface to run for
     */
    @Optional
    String iface

    /**
     * Specifies output forder for report created by runned tool
     */
    @Optional
    String outputFolder

    /**
     * SoapUI Properties.
     */
    @Optional
    Properties soapuiProperties

    ToolTask() {
        super('Runs soapUI tools')
    }

    @Override
    void executeAction() {

        SoapUIToolRunner runner = new SoapUIToolRunner('soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle Tool Runner')
        runner.projectFile = projectFile

        if (iface) {
            runner.interface = iface
        }

        if (tool) {
            runner.tool = tool
        }

        if (settingsFile) {
            runner.settingsFile = settingsFile
        }

        if (projectPassword) {
            runner.projectPassword = projectPassword
        }

        if (settingsPassword) {
            runner.soapUISettingsPassword = settingsPassword
        }

        if (outputFolder) {
            runner.outputFolder = outputFolder
        }

        if (soapuiProperties && soapuiProperties.size() > 0)
            for (Object key : soapuiProperties.keySet()) {
                println('Setting ' + (String) key + ' value ' + soapuiProperties.getProperty((String) key))
                System.setProperty((String) key, soapuiProperties.getProperty((String) key))
            }

        runner.run()
    }
}
