package com.eviware.soapui.gradle

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * TODO: Description
 * @author Sion Williams
 */
class SoapUIPluginTest extends Specification {
    Project project
    static final SOAP_TEST_TASK = 'soapTest'
    static final TOOL_TASK = 'tool'
    static final SECURITY_TEST_TASK = 'securityTest'
    static final MOCK_TASK = 'mock'
    static final LOAD_TEST_TASK = 'loadTest'

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "applies plugin and adds soapTest task"() {
        expect:
            project.tasks.findByName(SOAP_TEST_TASK) == null
        when:
            project.apply plugin: 'soapui'
        then:
            Task soapTesTask = project.tasks.findByName(SOAP_TEST_TASK)
            soapTesTask != null
    }

    def "applies plugin and adds mock task"() {
        expect:
            project.tasks.findByName(MOCK_TASK) == null
        when:
            project.apply plugin: 'soapui'
        then:
            Task mockTask = project.tasks.findByName(MOCK_TASK)
            mockTask != null
    }

    def "applies plugin and adds securityTest task"() {
        expect:
            project.tasks.findByName(SECURITY_TEST_TASK) == null
        when:
            project.apply plugin: 'soapui'
        then:
            Task securityTestTask = project.tasks.findByName(SECURITY_TEST_TASK)
            securityTestTask != null
    }

    def "applies plugin and adds tool task"() {
        expect:
            project.tasks.findByName(TOOL_TASK) == null
        when:
            project.apply plugin: 'soapui'
        then:
            Task toolTask = project.tasks.findByName(TOOL_TASK)
            toolTask != null
    }

    def "applies plugin and adds loadTest task"() {
        expect:
        project.tasks.findByName(LOAD_TEST_TASK) == null
        when:
        project.apply plugin: 'soapui'
        then:
        Task loadTestTask = project.tasks.findByName(LOAD_TEST_TASK)
        loadTestTask != null
    }
}
