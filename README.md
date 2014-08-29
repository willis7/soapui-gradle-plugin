# Gradle SoapUI plugin

![SoapUI Logo](http://www.soapui.org/images/stories/homepage/soapui_logo.png)

The plugin provides tasks for running SoapUI tests and mocks during a Gradle build.


## Build Status

[![Build Status](https://travis-ci.org/willis7/soapui-gradle-plugin.svg?branch=master)](https://travis-ci.org/willis7/soapui-gradle-plugin)


## Usage

To use the plugin's functionality, you will need to add the its binary artifact to your build script's classpath and apply the plugin.

### Adding the plugin binary to the build

The plugin JAR needs to be defined in the classpath of your build script. It is directly available on
[Bintray](https://bintray.com/sion5/gradle-plugins/soapui-gradle-plugin).
The following code snippet shows an example on how to retrieve it from Bintray:

```groovy
buildscript {
    repositories {
        // This order is important
        maven { url 'http://dl.bintray.com/sion5/gradle-plugins/' }
        maven { url "http://www.eviware.com/repository/maven2" }
        maven { url "http://repo.maven.apache.org/maven2" }
    }
    dependencies {
        classpath 'com.smartbear.soapui.gradle:soapui-gradle-plugin:VERSION'
    }
}
```

### Provided plugins


The `soapui` plugin helps you get started quickly. If you are OK if the preconfigured tasks, this is the
preferrable option. Most plugin users will go with this option. To use the Tomcat plugin, include the following code snippet
in your build script:

    apply plugin: 'soapui'


## Tasks

The `soapui` plugin pre-defines the following tasks out-of-the-box:

<table>
    <tr>
        <th>Task Name</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>soaptest</td>
        <td>TestTask</td>
        <td>Runs the SoapUI tests as specified by the plugin properties. Internally invokes the SoapUITestCaseRunner class as described there.</td>
    </tr>
    <tr>
        <td>loadtest</td>
        <td>LoadTestTask</td>
        <td>Runs the SoapUI loadtests as specified by the plugin properties. Internally invokes the SoapUILoadTestRunner class as described there.</td>
    </tr>
    <tr>
        <td>tool</td>
        <td>ToolTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIToolRunner class as described there.</td>
    </tr>
    <tr>
        <td>mock</td>
        <td>MockServiceTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIMockServiceRunner class as described there.</td>
    </tr>
</table>


## Task properties
### soaptest properties

To configure the SoapUI test task you can choose to set the following properties within the `soaptest` closure of the
`soapui` extension:

* `projectFile` : Specified the name of the SoapUI project file to use
* `testSuite` : Specifies the name of the TestSuite to run
* `testCase` : Specifies the name of the TestCase to run
* `endpoint` : Overrides the service endpoint to be invoked by any TestRequests
* `host` : Overrides the target host:port to be invoked by any TestRequests
* `username` : Overrides the username used by any TestRequests run
* `password` : Overrides the password used by any TestRequests run
* `domain` : Overrides the domain used by any TestRequests run
* `printReport` : Controls if a small test report should be printed to the console (true/false)
* `outputFolder` : Set which folder results/reports are saved to
* `junitReport` : Turns on creation of JUnit-reports, (true/false)
* `exportAll` : Controls if all test requests should be exported (default only exports errors), (true/false)
* `settingsFile` : Specifies SoapUI settings file to use
* `wssPasswordType` : Specifies WSS password type
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `globalProperties` : Sets global properties
* `projectProperties` : Sets project properties
* `saveAfterRun` : Saves project file after run
* `testFailIgnore` : Ignore failed tests.

### loadtest properties

To configure the SoapUI load test task you can choose to set the following properties within the `loadtest` closure of the
`soapui` extension:

* `projectFile` : Specified the name of the SoapUI project file to use
* `testSuite` : Specifies the name of the TestSuite to run
* `testCase` : Specifies the name of the TestCase to run
* `loadTest` : Specifies the name of the LoadTest to run
* `limit` : Overrides the limit of executed LoadTests
* `endpoint` : Overrides the service endpoint to be invoked by any TestRequests
* `host` : Overrides the target host:port to be invoked by any TestRequests
* `username` : Overrides the username used by any TestRequests run
* `password` : Overrides the password used by any TestRequests run
* `domain` : Overrides the domain used by any TestRequests run
* `printReport` : Controls if a small test report should be printed to the console (true/false)
* `outputFolder` : Set which folder results/reports are saved to
* `settingsFile` : Specifies SoapUI settings file to use
* `wssPasswordType` : Specifies WSS password type
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `saveAfterRun` : Saves project file after run
* `threadcount` : Number of threads in loadtest.

### tool properties

* `projectFile` : Specified the name of the SoapUI project file to use
* `iface` : Specifies the interface to generate for
* `tool` : Specifies the tool(s) to run, a comma-separated list of axis1, axis2, dotnet, gsoap, jaxb, wstools, wsconsume, ora, wscompile, wsi, wsimport, xfire or xmlbeans
* `settingsFile` : Specifies SoapUI settings file to use
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `outputFolder` : Set which folder results/reports are saved to

### mock properties
* `projectFile` : Specified the name of the SoapUI project file to use
* `mockService` : Specified the MockService to run
* `port` : The local port to listen on, overrides the port configured for the MockService
* `path` : The local path to listen on, overrides the path configured for the MockService
* `noBlock` : Turns off blocking when MockRunner has started
* `settingsFile` : Specifies SoapUI settings file to use
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `saveAfterRun` : Saves project file after run


## Full Example

```groovy
soapui {
    soaptest {
        projectFile = 'sample-soapui-project.xml'
        testSuite = 'OleTest'
        printReport = true
        junitReport = true
    }
    loadtest {
        projectFile = 'sample-soapui-load-project.xml'
        printReport = true
    }
    tool {
        projectFile = 'sample-soapui-tool-project.xml'
        iface = 'IOrderService'
        tool = 'wsi,axis1,axis2'
    }
}
```

## CI build


[![Built on Travis](http://about.travis-ci.org/images/travis-mascot-200px.png)](https://travis-ci.org/)

