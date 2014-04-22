package com.eviware.soapui.gradle.extensions

/**
 * Defines Tool task convention.
 *
 * @author Sion
 */
class SoapUIToolConvention {
    String tool
    String iface
    String outputFolder         // also, tool, securityTest,     , load
    Properties soapuiProperties // also, tool,             , mock
}
