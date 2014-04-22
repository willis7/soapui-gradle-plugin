package com.eviware.soapui.gradle.extensions

/**
 * Defines Security task convention.
 *
 * @author Sion
 */
class SoapUISecurityConvention {
    String securityTest
    String mockService
    String path
    String port
    boolean noBlock
    String testSuite            // also,     , securityTest,     , load
    String testCase             // also,     , securityTest,     , load
    String username             // also,     , securityTest,     , load
    String password             // also,     , securityTest,     , load
    String wssPasswordType      // also,     , securityTest,     , load
    String domain               // also,     , securityTest,     , load
    String host                 // also,     , securityTest,     , load
    String endpoint             // also,     , securityTest,     , load
    boolean interactive = false // also,     , securityTest
    boolean exportAll = true    // also,     , securityTest
    boolean junitReport = true  // also,     , securityTest
    boolean skip                // also,     , securityTest, mock, load
    String[] globalProperties   // also,     , securityTest, mock, load
    String[] projectProperties  // also,     , securityTest, mock, load
    boolean saveAfterRun = false    // also,     , securityTest, mock, load
    boolean printReport = true  // also,     , securityTest,     , load
    String outputFolder         // also, tool, securityTest,     , load
    boolean testFailIgnore = false  // also,     , securityTest
}
