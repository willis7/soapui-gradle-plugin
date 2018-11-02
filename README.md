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

<!--
[![Build Status](https://travis-ci.org/byte-shifter-ltd/soapui-gradle-plugin.svg)](https://travis-ci.org/byte-shifter-ltd/soapui-gradle-plugin)
-->
[![Build Status](https://travis-ci.org/willis7/soapui-gradle-plugin.svg)](https://travis-ci.org/willis7/soapui-gradle-plugin)
[![Coverage Status](https://coveralls.io/repos/github/byte-shifter-ltd/soapui-gradle-plugin/badge.svg?branch=master)](https://coveralls.io/github/byte-shifter-ltd/soapui-gradle-plugin?branch=master)


## Usage

This plugin has a fairly complex dependency tree. To use this plugin successfully we need to override some dependencies through forcing versions or completely substituting modules.
See approach [SmartBear uses solve jar-hell problem in their maven plugin.](http://smartbearsoftware.com/repository/maven2/com/smartbear/soapui/soapui-maven-plugin/5.3.0/soapui-maven-plugin-5.3.0.pom)
As a result your build file can look like this:

```groovy
buildscript {
    ext {
        soapUIVersion = '5.3.0.RELEASE' // open source version
        // soapUIVersion = '5.1.2.PRO-RELEASE' // pro version
    }
    repositories {
        maven { url 'https://plugins.gradle.org/m2/' }
        maven { url 'http://www.soapui.org/repository/maven2/' }
        mavenCentral()
    }
    dependencies {
        compile("com.smartbear.soapui:soapui:$soapUIVersion") {
            exclude group: 'com.jgoodies', module: 'forms'
            exclude group: 'com.jgoodies', module: 'looks'
            exclude group: 'com.jgoodies', module: 'binding'
        }
    }
    configurations.all {
        resolutionStrategy {
            force 'com.jgoodies:binding:2.0.1',
                  'com.jgoodies:forms:1.0.7',
                  'com.jgoodies:looks:2.2.0'
        }
    }
}

apply plugin: 'io.byteshifter.soapui'
```

But for most common and trivial use-cases, buildscript configuration could be much simpler:

```groovy
buildscript {
    repositories {
        jcenter()
        maven { url 'https://plugins.gradle.org/m2/' }
        maven { url 'http://smartbearsoftware.com/repository/maven2/' }
    }
    dependencies {
        classpath('gradle.plugin.io.byteshifter:soapui-gradle-plugin:5.3.0.RELEASE')
    }
}

apply plugin: io.byteshifter.plugins.soapui.SoapUIPlugin
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

import io.byteshifter.plugins.soapui.tasks.TestTask

task testSuiteA(type: TestTask) {
    testSuite = 'SuiteA'
}

task testSuiteB(type: TestTask) {
    testSuite = 'SuiteB'
}
```

What you should notice in the example above is that we still use the `soapui` convention block with the nested `test` section. You may also have noticed that we have defined 2 new tasks of type `TestTask`. The `TestTask` is what runs the `SoapUITestCaseRunner`. The only difference between the 2 tasks is that they set their own value for `testSuite`. Through the magic of convention mapping the rest of the values are inherited.


## Tons of TestSuites for enterprise-grade SoapUI test projects

In case of many TestSuites you might want use such approach to reduce a lot of duplications in your build script code:

```groovy
[
    'SuiteA',
    'SuiteB',
    // ...
    'SuiteZ',

].each { suite ->
    tasks.create(name: suite, type: io.byteshifter.plugins.soapui.tasks.TestTask) {
        testSuite = suite
    }
}
```

Please, note: to run all of the TestSuites in this case, you can use only `gradle soaptest` command.


## SoapUI test runner and plugin versions mapping

Previously, versions between soapui-gradle-plugin and SoapUI test runner was't synchronized.
But after version 5.0.1 we will try to keep them synchronized as soon as newer SoapUI will be released.

| soapui-gradle-plugin | SoapUI test runner |
| -------------------- | ------------------ |
| 0.2                  | 5.0.1              |
| 5.1.0                | 5.1.0              |
| ...                  | ...                |
| 5.3.1-RC             | 5.3.1-RC           |
| 5.3.0.RELEASE        | 5.3.0              |

Using Open Source Version:
We do recommend use SoapUI runner version 5.3.0 - it's last fully featured and quite stable version of SoapUI. 
Version 5.3.1-RC1 was not released.
Version 5.4.0 is very limited: LoadUI integration has been removed. 
To create and run advanced load tests, use LoadUI Pro, which is part of the ReadyAPI application suite.

## Contribute

- Issue Tracker: [github.com/byte-shifter-ltd/soapui-gradle-plugin/issues](https://github.com/byte-shifter-ltd/soapui-gradle-plugin/issues)
- Source Code: [github.com/byte-shifter-ltd/soapui-gradle-plugin](https://github.com/byte-shifter-ltd/soapui-gradle-plugin)


## License

The project is licensed under the MIT license.


<!-- nothing to thanks, JetBrains don't wanna give open-source licene for that project anymore..
## Thanks

For the Open Source licence ....
<img src="https://cdn.pbrd.co/images/8eFlobE.png" width="150" height="75" />
-->
