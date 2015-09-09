package io.byteshifter.plugins.soapui.extensions
/**
 * SoapUI plug-in extension to expose the properties and methods accessed in the extension block
 *
 * @author Sion Williams
 */
class SoapUIPluginExtension {

    // the following convention properties are added dynamically when the plugin is applied:
    // SoapUIToolConvention tool
    // SoapUISecurityConvention security
    // SoapUILoadConvention load
    // SoapUITestConvention test
    // SoapUIMockConvention mock
}
