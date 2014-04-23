package com.eviware.soapui.gradle.singleproject

import com.eviware.soapui.gradle.AbstractIntegrationTest
import spock.lang.Specification


/**
 * Single project integration test.
 *
 * @author Sion Williams
 */
abstract class SingleProjectBuildIntegrationTest extends AbstractIntegrationTest {
    def setup() {
        buildFile << """
apply plugin: com.eviware.soapui.gradle.SoapUIPlugin

"""
    }
}