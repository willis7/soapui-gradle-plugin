package com.eviware.soapui.gradle.extensions

/**
 * Defines Load task convention.
 *
 * @author Sion Williams
 */
class SoapUILoadConvention {
    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword
    String loadTest
    Integer limit
    Integer threadCount
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
    boolean saveAfterRun
    boolean printReport
    String outputFolder
}
