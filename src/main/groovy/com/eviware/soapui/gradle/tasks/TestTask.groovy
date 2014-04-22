package com.eviware.soapui.gradle.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUITestCaseRunner
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

/**
 * Runs soapUI functional tests
 * task name - soapTest
 * @author Sion Williams
 */
class TestTask extends DefaultTask {
    /**
     * The soapUI project file to test with
     * default-value="${project.artifactId}-soapui-project.xml"
     */
    String projectFile

    /**
     * The TestSuite to run project file to test with
     */
    String testSuite

    /**
     * The TestCase to run project file to test with
     */
    String testCase

    /**
     * The username to use for authentication challenges
     */
    String username

    /**
     * The password to use for authentication challenges
     */
    String password

    /**
     * The WSS password-type to use for any authentications. Setting this will
     * result in the addition of WS-Security UsernamePassword tokens to any
     * outgoing request containing the specified username and password. Set to
     * either 'Text' or 'Digest'
     */
    String wssPasswordType

    /**
     * The domain to use for authentication challenges
     */
    String domain

    /**
     * The host to use for requests
     */
    String host

    /**
     * Overrides the endpoint to use for requests
     */
    String endpoint

    /**
     * Sets the output folder for reports
     */
    String outputFolder

    /**
     * Turns on printing of reports
     */
    boolean printReport

    /**
     * Enabled interactive groovy scripts
     */
    boolean interactive

    /**
     * Turns on exporting of all results
     */
    boolean exportAll

    /**
     * Turns on creation of reports in junit style
     */
    boolean junitReport

    /**
     * Specifies soapUI settings file to use
     */
    String settingsFile

    /**
     * Tells Test Runner to skip tests.
     */
    boolean skip

    /**
     * Specifies password for encrypted soapUI project file
     */
    String projectPassword

    /**
     * Specifies password for encrypted soapui-settings file
     */
    String settingsPassword

    /**
     * If set ignore failed tests
     */
    boolean testFailIgnore

    /**
     * Specified global property values soapui.saveAfterRun
     */
    String[] globalProperties

    /**
     * Specified project property values
     */
    String[] projectProperties

    /**
     * Saves project file after running tests
     */
    boolean saveAfterRun

    /**
     * SoapUI Properties.
     */
    Properties soapuiProperties

    @TaskAction
    public void run() throws GradleException {

        if( projectFile == null )
        {
            throw new GradleException( "soapui-project-file setting is required" )
        }

        SoapUITestCaseRunner runner = new SoapUITestCaseRunner( "soapUI " + SoapUI.SOAPUI_VERSION
                + " Gradle TestCase Runner" )
        runner.setProjectFile( projectFile )

        if( endpoint != null )
            runner.setEndpoint( endpoint )

        if( testSuite != null )
            runner.setTestSuite( testSuite )

        if( testCase != null )
            runner.setTestCase( testCase )

        if( username != null )
            runner.setUsername( username )

        if( password != null )
            runner.setPassword( password )

        if( wssPasswordType != null )
            runner.setWssPasswordType( wssPasswordType )

        if( domain != null )
            runner.setDomain( domain )

        if( host != null )
            runner.setHost( host )

        if( outputFolder != null )
            runner.setOutputFolder( outputFolder )

        runner.setPrintReport( printReport )
        runner.setExportAll( exportAll )
        runner.setJUnitReport( junitReport )
        runner.setEnableUI( interactive )
        runner.setIgnoreError( testFailIgnore )
        runner.setSaveAfterRun( saveAfterRun )

        if( settingsFile != null )
            runner.setSettingsFile( settingsFile )

        if( projectPassword != null )
            runner.setProjectPassword( projectPassword )

        if( settingsPassword != null )
            runner.setSoapUISettingsPassword( settingsPassword )

        if( globalProperties != null )
            runner.setGlobalProperties( globalProperties )

        if( projectProperties != null )
            runner.setProjectProperties( projectProperties )

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
        catch( Exception e )
        {
            getLog().error( e.toString() );
            throw new GradleException( "SoapUI Test(s) failed" + e.getMessage() )
        }
    }
}
