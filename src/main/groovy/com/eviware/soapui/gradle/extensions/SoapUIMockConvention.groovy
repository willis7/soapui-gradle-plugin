package com.eviware.soapui.gradle.extensions

/**
 * TODO: Description
 * @author Sion
 */
class SoapUIMockConvention {
    boolean skip                // also,     , securityTest, mock, load
    String[] globalProperties   // also,     , securityTest, mock, load
    String[] projectProperties  // also,     , securityTest, mock, load
    boolean saveAfterRun = false    // also,     , securityTest, mock, load
    Properties soapuiProperties // also, tool,             , mock
}
