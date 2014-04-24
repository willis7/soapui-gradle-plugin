package com.eviware.soapui.gradle.extensions

/**
 * Defines Security task convention.
 *
 * @author Sion
 */
class SoapUISecurityConvention {

    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword

    String securityTest
    String mockService
    String path
    String port
    boolean noBlock
    String testSuite
    String testCase
    String username
    String password
    String wssPasswordType
    String domain
    String host
    String endpoint
    boolean interactive
    boolean exportAll
    boolean junitReport
    boolean skip
    String[] globalProperties
    String[] projectProperties
    boolean saveAfterRun
    boolean printReport
    String outputFolder
    boolean testFailIgnore
}
