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

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Abstract SoapUI task
 *
 * @author Sion Williams
 */
abstract class SoapUITask extends DefaultTask {

    /**
     * The soapUI project file to test with
     */
    @Input
    String projectFile

    /**
     * Specifies soapUI settings file to use
     */
    @Optional
    String settingsFile

    /**
     * Specifies password for encrypted soapUI project file
     */
    @Optional
    String projectPassword

    /**
     * Specifies password for encrypted soapui-settings file
     */
    @Optional
    String settingsPassword

    SoapUITask(String description) {
        this.description = description
        group = 'SoapUI'
    }

    @TaskAction
    void run() {
        executeAction()

    }

    /**
     * Catches exception and handles it
     */
    private void withExceptionHandling(Closure c) {
        if (!getProjectFile()) {
            logger.error "soapui-project-file setting is required"
            throw new GradleException('soapui-project-file setting is required')
        }

        try {
            c
        }
        catch (Exception e) {
            logger.error( "Exception caught:", e )
            throw new GradleException(e.message)
        }
    }

    /**
     * Abstract method required to be implemented by subclass
     */
    abstract void executeAction()
}
