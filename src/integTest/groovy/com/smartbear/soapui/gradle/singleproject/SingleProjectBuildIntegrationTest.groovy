package com.smartbear.soapui.gradle.singleproject

import com.smartbear.soapui.gradle.AbstractIntegrationTest

/**
 * Single project integration test.
 *
 * @author Sion Williams
 */
abstract class SingleProjectBuildIntegrationTest extends AbstractIntegrationTest {
    def setup() {
        buildFile << """
apply plugin: com.smartbear.soapui.gradle.SoapUIPlugin

"""
    }
}