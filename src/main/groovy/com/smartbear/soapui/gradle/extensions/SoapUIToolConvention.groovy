package com.smartbear.soapui.gradle.extensions

/**
 * Defines Tool task convention.
 *
 * @author Sion
 */
class SoapUIToolConvention {
    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword

    String tool
    String iface
    String outputFolder
    Properties soapuiProperties
}
