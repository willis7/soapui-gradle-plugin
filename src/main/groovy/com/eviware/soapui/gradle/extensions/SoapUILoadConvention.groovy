package com.eviware.soapui.gradle.extensions

/**
 * TDefines Load task convention.
 *
 * @author Sion
 */
class SoapUILoadConvention {
    String loadTest
    Integer limit
    Integer threadCount

    String testSuite            // also,     , securityTest,     , load
    String testCase             // also,     , securityTest,     , load
    String username             // also,     , securityTest,     , load
    String password             // also,     , securityTest,     , load
    String wssPasswordType      // also,     , securityTest,     , load
    String domain               // also,     , securityTest,     , load
    String host                 // also,     , securityTest,     , load
    String endpoint             // also,     , securityTest,     , load

    boolean skip                // also,     , securityTest, mock, load
    String[] globalProperties   // also,     , securityTest, mock, load
    String[] projectProperties  // also,     , securityTest, mock, load

    boolean saveAfterRun = false    // also,     , securityTest, mock, load

    boolean printReport = true  // also,     , securityTest,     , load
    String outputFolder         // also, tool, securityTest,     , load
}
