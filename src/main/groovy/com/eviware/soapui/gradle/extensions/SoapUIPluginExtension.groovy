package com.eviware.soapui.gradle.extensions
/**
 * SoapUI plug-in extension to expose the properties and methods accessed in the extension block
 *
 * @author Sion Williams
 */
class SoapUIPluginExtension {

    SoapUIToolConvention tool = new SoapUIToolConvention()
    SoapUISecurityConvention security = new SoapUISecurityConvention()
    SoapUILoadConvention load = new SoapUILoadConvention()
    SoapUITestConvention test = new SoapUITestConvention()
    SoapUIMockConvention mock = new SoapUIMockConvention()

    def tool(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = tool
        closure()
    }

    def security(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = security
        closure()
    }

    def load(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = load
        closure()
    }

    def test(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = test
        closure()
    }

    def mock(Closure closure) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = mock
        closure()
    }
}
