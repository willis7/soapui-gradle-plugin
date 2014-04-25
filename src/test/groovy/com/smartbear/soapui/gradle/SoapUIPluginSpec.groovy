package com.smartbear.soapui.gradle

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Specification for the plugin implementation
 *
 * @author Sion Williams
 */
class SoapUIPluginSpec extends Specification {
    Project project
    static final SOAP_TEST_TASK = 'soaptest'
    static final TOOL_TASK = 'tool'
    static final SECURITY_TEST_TASK = 'securitytest'
    static final MOCK_TASK = 'mock'
    static final LOAD_TEST_TASK = 'loadtest'

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "applies plugin and adds soapTest task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(SOAP_TEST_TASK) == null

        when: "the plugin is added to the project"
        project.apply(plugin: 'soapui')
        project.soapui {
            test {
                projectFile = 'sample-soapui-project.xml'
                printReport = true
                exportAll = true
                junitReport = true
                interactive = false
                testFailIgnore = true
                saveAfterRun = false
            }
        }
        then: "the task should be available and configured"
        Task task = project.tasks.findByName(SOAP_TEST_TASK)
        task != null
        task.group == 'SoapUI'
        task.description == 'Runs soapUI functional tests'

        task.projectFile == 'sample-soapui-project.xml'
        task.printReport == true
        task.exportAll == true
        task.junitReport == true
        task.interactive == false
        task.testFailIgnore == true
        task.saveAfterRun == false
    }

    def "applies plugin but fails when projectFile not defined for soapTest Task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(SOAP_TEST_TASK) == null

        when: "the plugin is added to the project and the task run"
        project.apply(plugin: 'soapui')
        Task task = project.tasks.findByName(SOAP_TEST_TASK)
        task.run()

        then: "a gradle exception should be thrown"
        task != null
        thrown(GradleException)
    }

    def "applies plugin and adds tool task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(TOOL_TASK) == null

        when: "the plugin is added to the project"
        project.apply plugin: 'soapui'

        project.soapui {
            tool {
                projectFile = 'sample-soapui-project.xml'
                tool = 'wsi,axis1,axis2'
                iface = 'IOrderService'
            }
        }

        then: "the task should be available and configured"
        Task task = project.tasks.findByName(TOOL_TASK)
        task != null
        task.group == 'SoapUI'
        task.description == 'Runs soapUI tools'

        task.projectFile == 'sample-soapui-project.xml'
        task.tool == 'wsi,axis1,axis2'
        task.iface == 'IOrderService'
    }

    def "applies plugin but fails when projectFile not defined for tool Task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(TOOL_TASK) == null

        when: "the plugin is added to the project and the task run"
        project.apply(plugin: 'soapui')
        Task task = project.tasks.findByName(TOOL_TASK)
        task.run()

        then: "a gradle exception should be thrown"
        task != null
        thrown(GradleException)
    }

    def "applies plugin and adds mock task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(MOCK_TASK) == null

        when: "the plugin is added to the project"
        project.apply plugin: 'soapui'

        then: "the task should be available and configured"
        Task task = project.tasks.findByName(MOCK_TASK)
        task != null
    }

    def "applies plugin and adds loadTest task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(LOAD_TEST_TASK) == null

        when: "the plugin is added to the project"
        project.apply plugin: 'soapui'

        then: "the task should be available and configured"
        Task task = project.tasks.findByName(LOAD_TEST_TASK)
        task != null
    }

    def "applies plugin and adds securityTest task"() {
        expect: "no task to be found initially"
        project.tasks.findByName(SECURITY_TEST_TASK) == null

        when: "the plugin is added to the project"
        project.apply plugin: 'soapui'

        then: "the task should be available and configured"
        Task task = project.tasks.findByName(SECURITY_TEST_TASK)
        task != null
    }

}
