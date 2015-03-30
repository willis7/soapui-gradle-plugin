package com.lv.plugins.soapui

import com.lv.plugins.soapui.extensions.SoapUIPluginExtension
import nebula.test.PluginProjectSpec

/**
 * Specification for the plugin implementation
 *
 * @author Sion Williams
 */
class SoapUIPluginSpec extends PluginProjectSpec  {
    static final String PLUGIN_ID = 'com.lv.soapui'

    @Override
    String getPluginName() {
        return PLUGIN_ID
    }

    def setup() {
        project.apply plugin: pluginName
    }

    def "apply creates soapui extension"() {
        expect: project.extensions.findByName( 'soapui' )
    }

    def "soapui extension has test extension"() {
        setup: SoapUIPluginExtension soapUIPluginExtension = project.extensions.findByName( 'soapui' )

        expect: soapUIPluginExtension.test
    }

    def "soapui extension has mock extension"() {
        setup: SoapUIPluginExtension soapUIPluginExtension = project.extensions.findByName( 'soapui' )

        expect: soapUIPluginExtension.mock
    }

    def "soapui extension has security extension"() {
        setup: SoapUIPluginExtension soapUIPluginExtension = project.extensions.findByName( 'soapui' )

        expect: soapUIPluginExtension.security
    }

    def "soapui extension has load extension"() {
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.findByName( 'soapui' )

        expect: soapUIPluginExtension.load
    }

    def "soapui extension has tool extension"() {
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.findByName( 'soapui' )

        expect: soapUIPluginExtension.tool
    }

    def "apply creates soaptest task"() {
        expect: project.tasks.findByName( 'soaptest' )
    }

    def "apply creates tool task"() {
        expect: project.tasks.findByName( 'soaptool' )
    }

    def "apply creates mock task"() {
        expect: project.tasks.findByName( 'soapmock' )
    }

    def "apply creates loadtest task"() {
        expect: project.tasks.findByName( 'soaploadtest' )
    }

    def "apply creates securitytest task"() {
        expect: project.tasks.findByName( 'soapsecuritytest' )
    }

}
