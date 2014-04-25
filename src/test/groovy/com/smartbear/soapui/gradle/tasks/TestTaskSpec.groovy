package com.smartbear.soapui.gradle.tasks

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Specification for the soaptest task
 *
 * @author Sion Williams
 */
class TestTaskSpec extends Specification {
    Project project
    final static TASK_NAME = 'soaptest'

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "add soapTest tasks to project"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null
        when:
        project.task(TASK_NAME, type: TestTask) {
            projectFile = 'sample-soapui-project.xml'
            printReport = true
            exportAll = true
            junitReport = true
            interactive = false
            testFailIgnore = true
            saveAfterRun = false
        }
        then:
        Task task = project.tasks.findByName(TASK_NAME)
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

    def "run soapTest with no project.xml defined"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null
        when:
        Task task = project.task(TASK_NAME, type: TestTask) {
            printReport = true
            exportAll = true
            junitReport = true
            interactive = false
            testFailIgnore = true
            saveAfterRun = false
        }
        task.run()

        then:
        project.tasks.findByName(TASK_NAME) != null
        thrown(GradleException)
    }
}
