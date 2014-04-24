package com.eviware.soapui.gradle.extensions

/**
 * TODO: Description
 * @author Sion
 */
class SoapUIMockConvention {
    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword
    boolean skip
    String[] globalProperties
    String[] projectProperties
    boolean saveAfterRun
    Properties soapuiProperties
}
