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

import com.eviware.soapui.SoapUIProToolRunner
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

        SoapUIProToolRunner runner = new SoapUIProToolRunner('soapUI Pro 5.1.2 Gradle Tool Runner')
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

        if (soapuiProperties && soapuiProperties.size() > 0) {
            for (Object key : soapuiProperties.keySet()) {
                println('Setting ' + (String) key + ' value ' + soapuiProperties.getProperty((String) key))
                System.setProperty((String) key, soapuiProperties.getProperty((String) key))
            }
        }

        runner.run()
    }
}
