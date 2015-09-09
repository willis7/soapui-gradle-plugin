package io.byteshifter.plugins.soapui.extensions

/**
 * Defines the mocking service convention
 *
 * @author Sion Williams
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
