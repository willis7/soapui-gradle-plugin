package com.lv.plugins.soapui.tasks

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by Sion on 25/04/2014.
 */
class SecurityTestTaskSpec extends Specification {
    Project project
    final static TASK_NAME = 'securitytest'

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "add mock task to project"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null
        when:
        project.task(TASK_NAME, type: SecurityTestTask) {
            projectFile = 'sample-soapui-project.xml'
        }
        then:
        Task task = project.tasks.findByName(TASK_NAME)
        task != null
        task.group == 'SoapUI'
        task.description == 'Runs soapUI security tests'

        task.projectFile == 'sample-soapui-project.xml'
    }
}
