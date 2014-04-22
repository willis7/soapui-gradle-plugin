package com.eviware.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUIMockServiceRunner
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Runs soapUI mockservice
 * task name - mock
 * @author Sion Williams
 */
class MockServiceTask extends DefaultTask{
    /**
     * The soapUI project file to test with
     */
    String projectFile

    /**
     * The mockservice to run
     */
    @Optional
    String mockService

    /**
     * The path to listen on
     */
    @Optional
    String path

    /**
     * The port to listen on
     */
    @Optional
    String port

    /**
     * Specifies soapUI settings file to use
     */
    @Optional
    String settingsFile

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
     * Specifies password for encrypted soapUI project file
     */
    @Optional
    String projectPassword

    /**
     * Specifies password for encrypted soapui-settings file
     */
    @Optional
    String settingsPassword

    /**
     * Specified global property values
     */
    @Optional
    String [] globalProperties

    /**
     * Specified project property values
     */
    @Optional
    String [] projectProperties

    /**
     * Saves project file after running tests
     */
    boolean saveAfterRun

    /**
     * SoapUI Properties.
     */
    @Optional
    Properties soapuiProperties

    @TaskAction
    public void run() throws GradleException {

        if( projectFile == null )
        {
            throw new GradleException("soapui-project-file setting is required" );
        }

        SoapUIMockServiceRunner runner = new SoapUIMockServiceRunner(
                "soapUI " + SoapUI.SOAPUI_VERSION + " Gradle MockService Runner");
        runner.setProjectFile( projectFile );


        if( mockService != null )
            runner.setMockService( mockService );

        if( path != null )
            runner.setPath( path );

        if( port != null )
            runner.setPort( port );

        if( settingsFile != null )
            runner.setSettingsFile( settingsFile );

        runner.setBlock( !noBlock );
        runner.setSaveAfterRun( saveAfterRun );

        if( projectPassword != null )
            runner.setProjectPassword(projectPassword);

        if ( settingsPassword != null )
            runner.setSoapUISettingsPassword(settingsPassword);

        if( globalProperties != null )
            runner.setGlobalProperties(globalProperties);

        if( projectProperties != null )
            runner.setProjectProperties(projectProperties);

        if( soapuiProperties != null && soapuiProperties.size() > 0 )
            for( Object key : soapuiProperties.keySet() )
            {
                System.out.println( "Setting " + ( String )key + " value " + soapuiProperties.getProperty( ( String )key ) );
                System.setProperty( ( String )key, soapuiProperties.getProperty( ( String )key ) );
            }

        try
        {
            runner.run();
        }
        catch (Exception e)
        {
            logger.error( e.toString() );
            throw new GradleException( "SoapUI MockService(s) failed" + e.getMessage() );
        }
    }
}
