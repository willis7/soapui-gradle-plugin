package com.smartbear.soapui.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
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

        if (!getProjectFile()) {
            throw new GradleException('soapui-project-file setting is required')
        }

        withExceptionHandling {
            executeAction()
        }
    }

    /**
     * Catches exception and handles it
     */
    private void withExceptionHandling(Closure c) {
        try {
            c
        }
        catch (Exception e) {
            throw new GradleException(e.message)
        }
    }

    /**
     * Abstract method required to be implemented by subclass
     */
    abstract void executeAction()
}
