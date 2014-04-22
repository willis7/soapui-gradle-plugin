package com.eviware.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUIToolRunner
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Runs soapUI tools
 * task name - tool
 *
 * @author Sion
 */
class ToolTask extends DefaultTask {
    /**
     * The soapUI project file to test with
     * default-value="${project.artifactId}-soapui-project.xml"
     */
    String projectFile;

    /**
     * The tool to run
     */
    @Optional
    String tool;

    /**
     * The interface to run for
     */
    @Optional
    String iface;

    /**
     * Specifies soapUI settings file to use
     */
    @Optional
    String settingsFile;

    /**
     * Specifies password for encrypted soapUI project file
     */
    @Optional
    String projectPassword;

    /**
     * Specifies password for encrypted soapui-settings file
     */
    @Optional
    String settingsPassword;

    /**
     * Specifies output forder for report created by runned tool
     */
    @Optional
    String outputFolder;

    /**
     * SoapUI Properties.
     */
    @Optional
    Properties soapuiProperties;

    @TaskAction
    public void run() throws GradleException {
        if ( !projectFile )
        {
            throw new GradleException('soapui-project-file setting is required')
        }

        SoapUIToolRunner runner = new SoapUIToolRunner('soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle Tool Runner')
        runner.projectFile = projectFile

        if (iface){
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

        if( soapuiProperties && soapuiProperties.size() > 0 )
            for( Object key : soapuiProperties.keySet() )
            {
                println( 'Setting ' + ( String )key + ' value ' + soapuiProperties.getProperty( ( String )key ) )
                System.setProperty( ( String )key, soapuiProperties.getProperty( ( String )key ) )
            }

        try
        {
            runner.run()
        }
        catch (Exception e)
        {
            logger.error(e.toString())
            throw new GradleException( "SoapUI Tool(s) failed" + e.message)
        }
    }
}
