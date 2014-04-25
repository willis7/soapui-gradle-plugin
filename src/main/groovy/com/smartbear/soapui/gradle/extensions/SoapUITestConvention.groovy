package com.smartbear.soapui.gradle.extensions

/**
 * Defines soapui test task convention.
 *
 * @author Sion Williams
 */
class SoapUITestConvention {
    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword

    String testSuite
    String testCase
    String username
    String password
    String wssPasswordType
    String domain
    String host
    String endpoint
    boolean skip
    String[] globalProperties
    String[] projectProperties
    String outputFolder
    Properties soapuiProperties
    boolean interactive
    boolean exportAll
    boolean junitReport
    boolean saveAfterRun
    boolean printReport
    boolean testFailIgnore
}
