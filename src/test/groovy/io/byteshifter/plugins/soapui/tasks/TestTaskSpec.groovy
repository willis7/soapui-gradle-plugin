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
package io.byteshifter.plugins.soapui.tasks

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Specification for the soaptest task
 *
 * @author Sion Williams
 */
class TestTaskSpec extends Specification {
    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "add soapTest tasks to project"() {
        expect:
        project.tasks.findByName( 'soaptest' ) == null
        when:
        project.task( 'soaptest', type: TestTask ) {
            projectFile = 'sample-soapui-project.xml'
            printReport = true
            exportAll = true
            junitReport = true
            interactive = false
            testFailIgnore = true
            saveAfterRun = false
            environment = 'dev'
        }

        then:
        Task task = project.tasks.findByName( 'soaptest' )
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
        task.environment == 'dev'
    }

    //TODO Convert to integTest
    @Ignore
    def "run soapTest with no project.xml defined"() {
        expect:
        project.tasks.findByName( 'soaptest' ) == null

        when:
        Task task = project.task( 'soaptest', type: TestTask) {
            printReport = true
            exportAll = true
            junitReport = true
            interactive = false
            testFailIgnore = true
            saveAfterRun = false
            environment = 'dev'
        }
        task.run()

        then:
        project.tasks.findByName( 'soaptest' ) != null
        thrown(GradleException)
    }
}
