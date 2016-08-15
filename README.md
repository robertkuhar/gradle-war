gradle-war
=================

The intent of gradle-war is to build out a skeleton Gradle based WAR project
leveraging Servlet 3.0, Gradle, TestNG, and SLF4J on Logback.  Ultimately we
would like to make this whole application Spring Boot based.

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
