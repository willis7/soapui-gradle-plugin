/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Sion Williams
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.byteshifter.plugins.soapui

import io.byteshifter.plugins.soapui.extensions.SoapUIPluginExtension
import nebula.test.PluginProjectSpec

/**
 * Specification for the plugin implementation
 *
 * @author Sion Williams
 */
class SoapUIPluginSpec extends PluginProjectSpec  {
    static final String PLUGIN_ID = 'io.byteshifter.soapui'

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
