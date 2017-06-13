gradle-war
=================

The intent of gradle-war is to build out a skeleton Gradle based WAR project
leveraging Servlet 3.1, Gradle, TestNG, and SLF4J on Logback.

To build the project execute the gradle wrapper ```gradlew``` on *nix,
```gradlew.bat``` on Windows.  The default tasks are 'clean', 'build'. 

```
$ ./gradlew
:clean
:compileJava
:processResources
...
BUILD SUCCESSFUL

Total time: 12.764 secs
```

To run the WAR:

```
$ ./gradlew jettyRun
Starting a Gradle Daemon (subsequent builds will be faster)
The Jetty plugin has been deprecated and is scheduled to be removed in Gradle 4.0. Consider using the Gretty (https://github.com/akhikhl/gretty) plugin instead.
        at build_7qlb787wpjzjnz8vbay375bqj.run(/Users/robert.kuhar/dev/gradle-war/build.gradle:5)
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
<=========----> 75% EXECUTING
> :jettyRun > Running at http://localhost:8080/gradle-war
```


TODO:  Overcome the Jetty plugin deprecation
