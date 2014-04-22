package com.eviware.soapui.gradle.extensions

/**
 * TODO: Description
 * @author Sion
 */
class SoapUITestConvention {
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
