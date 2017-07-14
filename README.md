# Gradle SoapUI plugin

![SoapUI Logo](http://www.soapui.org/images/stories/homepage/soapui_logo.png)

The plugin provides tasks for running SoapUI tests and mocks during a Gradle build.


<table border=1>
    <tr>
        <td>
            Unfortunately, I don't have much time to contribute anymore. In practice this means far less activity, 
            responsiveness on issues and new releases from my end.
        </td>
    </tr>
    <tr>
        <td>
            I am  ctively looking for contributors willing to take on maintenance and implementation of the project. If you are interested and would love to see this 
            plugin continue to thrive, shoot me a <a href="mailto:sion5@hotmail.co.uk">mail</a>.
        </td>
    </tr>
</table>



## Build Status

[![Build Status](https://travis-ci.org/byte-shifter-ltd/soapui-gradle-plugin.svg)](https://travis-ci.org/byte-shifter-ltd/soapui-gradle-plugin)
[![Coverage Status](https://coveralls.io/repos/github/byte-shifter-ltd/soapui-gradle-plugin/badge.svg?branch=master)](https://coveralls.io/github/byte-shifter-ltd/soapui-gradle-plugin?branch=master)

## Usage

This plugin has a fairly complex dependency tree. To use this plugin successfully we need to override some dependencies through forcing versions or completely substituting modules. As a result your build file will need to look like this:

```
buildscript {
  repositories {
    maven { url "https://plugins.gradle.org/m2/" }
    maven { url "http://www.soapui.org/repository/maven2/" }
    mavenCentral()
  }
  dependencies {
    classpath ("gradle.plugin.io.byteshifter:soapui-gradle-plugin:5.1.2") {
        exclude module: 'cajo'
        exclude group: 'org.codehaus.groovy'
      }
  }
  configurations.all {
      resolutionStrategy {
          // force certain versions of dependencies (including transitive)
          force 'com.jgoodies:forms:1.1.0',
                  'com.fifesoft:rsyntaxtextarea:2.5.8',
                  'xalan:xalan:2.7.2',
                  'joda-time:joda-time:2.0',
                  'org.slf4j:slf4j-api:1.6.2'

          // add dependency substitution rules
          dependencySubstitution {
              substitute module('jtidy:jtidy:r872-jdk15') with module('net.sf.jtidy:jtidy:r938')
              substitute module('jetty:jetty:6.1.26') with module('org.mortbay.jetty:jetty:6.1.26')
          }
      }
  }
}

apply plugin: 'io.byteshifter.soapui'
```


[Gradle Plugin Portal](https://plugins.gradle.org/plugin/io.byteshifter.soapui)


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
        <td>soapload</td>
        <td>LoadTestTask</td>
        <td>Runs the SoapUI loadtests as specified by the plugin properties. Internally invokes the SoapUILoadTestRunner class as described there.</td>
    </tr>
    <tr>
        <td>soaptool</td>
        <td>ToolTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIToolRunner class as described there.</td>
    </tr>
    <tr>
        <td>soapmock</td>
        <td>MockServiceTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIMockServiceRunner class as described there.</td>
    </tr>
</table>


## Task properties
### soaptest properties

To configure the SoapUI test task you can choose to set the following properties within the `test` closure of the
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

To configure the SoapUI load test task you can choose to set the following properties within the `load` closure of the
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
    test {
        projectFile = 'sample-soapui-project.xml'
        testSuite = 'OleTest'
        printReport = true
        junitReport = true
    }
    load {
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

## Complex Example

There may be times when you have multiple test suites inside the same SoapUI project. You wouldn't want to maintain several Gradle projects, so the plugin uses convention mapping. This means you can have many tasks, but override the properties at runtime. Here's an example:

```groovy
soapui {
    test {
        projectFile = 'sample-soapui-project.xml'
        printReport = true
        junitReport = true
    }
}

task testSuiteA(type: TestTask) {
    testSuite = 'SuiteA'
}

task testSuiteB(type: TestTask) {
    testSuite = 'SuiteB'
}
```
What you should notice in the example above is that we still use the `soapui` convention block with the nested `test` section. You may also have noticed that we have defined 2 new tasks of type `TestTask`. The `TestTask` is what runs the `SoapUITestCaseRunner`. The only difference between the 2 tasks is that they set their own value for `testSuite`. Through the magic of convention mapping the rest of the values are inherited.


## SoapUI test runner and plugin versions mapping

Previously, versions between soapui-gradle-plugin and SoapUI test runner was't synchronized.
But after version 5.0.1 we will try to keep them synchronized as soon as newer SoapUI will be released.

| soapui-gradle-plugin | SoapUI test runner |
| -------------------- | ------------------ |
| 0.2                  | 5.0.1              |
| 5.1.0                | 5.1.0              |
| .....                | .....              |
| 5.3.0                | 5.3.0              |
| 5.3.1-RC             | 5.3.1-RC           |


## Contribute

- Issue Tracker: [github.com/byte-shifter-ltd/soapui-gradle-plugin/issues](https://github.com/byte-shifter-ltd/soapui-gradle-plugin/issues)
- Source Code: [github.com/byte-shifter-ltd/soapui-gradle-plugin](https://github.com/byte-shifter-ltd/soapui-gradle-plugin)


## License

The project is licensed under the MIT license.

## Thanks

For the Open Source licence ....
<img src="https://cdn.pbrd.co/images/8eFlobE.png" width="150" height="75" />
