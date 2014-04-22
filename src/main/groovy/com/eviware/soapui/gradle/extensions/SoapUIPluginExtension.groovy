package com.eviware.soapui.gradle.extensions

import com.eviware.soapui.gradle.extensions.SoapUILoadConvention
import com.eviware.soapui.gradle.extensions.SoapUISecurityConvention
import com.eviware.soapui.gradle.extensions.SoapUITestConvention
import com.eviware.soapui.gradle.extensions.SoapUIToolConvention

/**
 * SoapUI plug-in extension to expose the properties and methods accessed in the extension block
 *
 * @author Sion Williams
 */
class SoapUIPluginExtension {
    /*
    * All Task Convention
    */
    String projectFile
    String settingsFile
    String projectPassword
    String settingsPassword

    SoapUIToolConvention tool = new SoapUIToolConvention()
    SoapUISecurityConvention security = new SoapUISecurityConvention()
    SoapUILoadConvention load = new SoapUILoadConvention()
    SoapUITestConvention test = new SoapUITestConvention()

    def tool( Closure closure ) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = tool
        closure()
    }

    def security( Closure closure ) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = security
        closure()
    }

    def load( Closure closure ) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = load
        closure()
    }

    def test( Closure closure ) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = test
        closure()
    }
}
