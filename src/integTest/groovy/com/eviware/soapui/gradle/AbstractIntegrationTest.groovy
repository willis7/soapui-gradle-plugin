package com.eviware.soapui.gradle

import spock.lang.Specification
import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.GradleProject
import static org.spockframework.util.Assert.fail

/**
 * Abstract integration test using Gradle's tooling API.
 *
 * @author Sion Williams
 */
abstract class AbstractIntegrationTest extends Specification {
    File integTestDir
    File buildFile

    def setup() {
        integTestDir = new File('build/integTest')

        if(!integTestDir.deleteDir()) {
            fail('Unable to delete integration test directory.')
        }

        if(!integTestDir.mkdirs()) {
            fail('Unable to create integration test directory.')
        }

        buildFile = createNewFile(integTestDir, 'build.gradle')

        buildFile << """
buildscript {
    dependencies {
        classpath files('../classes/main')
    }
}

"""
    }

    /*
     * Utility task for creating a new directory when parent directory provided
     *
     * @param parent File
     * @param dirname String
     */
    protected File createNewDir(File parent, String dirname) {
        File dir = new File(parent, dirname)

        if(!dir.exists()) {
            if(!dir.mkdirs()) {
                fail("Unable to create new test directory $dir.canonicalPath.")
            }
        }

        dir
    }

    /*
     * Utility task for creating a new file when parent directory provided
     *
     * @param parent File
     * @param filename String
     */
    protected File createNewFile(File parent, String filename) {
        File file = new File(parent, filename)

        if(!file.exists()) {
            if(!file.createNewFile()) {
                fail("Unable to create new test file $file.canonicalPath.")
            }
        }

        file
    }

    /*
     * assert file list exists in directory
     *
     * @param dir File
     * @param requiredFilenames List<String>
     */
    protected void assertExistingFiles(File dir, List<String> requiredFilenames) {
        assertExistingDirectory(dir)
        def dirFileNames = dir.listFiles()*.name

        requiredFilenames.each { filename ->
            assert dirFileNames.find { it ==~ filename }
        }
    }

    /*
     * assert directory exists
     *
     * @param dir File
     */
    private void assertExistingDirectory(File dir) {
        if(!dir || !dir.exists()) {
            fail("Unable to check target directory '${dir?.canonicalPath}' for files.")
        }
    }

    /*
     * @param projectDir File
     * @param tasks String...
     */
    protected GradleProject runTasks(File projectDir, String... tasks) {
        ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(projectDir).connect()

        try {
            BuildLauncher builder = connection.newBuild()
            builder.forTasks(tasks).run()
            return connection.getModel(GradleProject)
        }
        finally {
            connection?.close()
        }
    }
}