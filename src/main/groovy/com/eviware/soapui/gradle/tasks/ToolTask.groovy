package com.eviware.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUIToolRunner
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
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
    String tool;

    /**
     * The interface to run for
     */
    String iface;

    /**
     * Specifies soapUI settings file to use
     */
    String settingsFile;

    /**
     * Specifies password for encrypted soapUI project file
     */
    String projectPassword;

    /**
     * Specifies password for encrypted soapui-settings file
     */
    String settingsPassword;

    /**
     * Specifies output forder for report created by runned tool
     */
    String outputFolder;

    /**
     * SoapUI Properties.
     */
    Properties soapuiProperties;

    @TaskAction
    public void run() throws GradleException {
        if (projectFile == null)
        {
            throw new GradleException("soapui-project-file setting is required")
        }

        SoapUIToolRunner runner = new SoapUIToolRunner("soapUI " + SoapUI.SOAPUI_VERSION + " Gradle Tool Runner");
        runner.setProjectFile(projectFile)

        if (iface != null)
            runner.setInterface(iface)

        if (tool != null)
            runner.setTool(tool)

        if (settingsFile != null)
            runner.setSettingsFile(settingsFile)

        if (projectPassword != null)
            runner.setProjectPassword(projectPassword)

        if (settingsPassword != null)
            runner.setSoapUISettingsPassword(settingsPassword)

        if (outputFolder != null)
            runner.setOutputFolder(outputFolder)

        if( soapuiProperties != null && soapuiProperties.size() > 0 )
            for( Object key : soapuiProperties.keySet() )
            {
                System.out.println( "Setting " + ( String )key + " value " + soapuiProperties.getProperty( ( String )key ) )
                System.setProperty( ( String )key, soapuiProperties.getProperty( ( String )key ) )
            }

        try
        {
            runner.run();
        }
        catch (Exception e)
        {
            logger.error(e.toString())
            throw new GradleException( "SoapUI Tool(s) failed" + e.getMessage())
        }
    }
}
