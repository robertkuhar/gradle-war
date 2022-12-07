import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.language.base.plugins.LifecycleBasePlugin.VERIFICATION_GROUP

plugins {
    java
    eclipse
    idea
    war
    id("org.gretty") version "3.1.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

version = "1.1"

defaultTasks("clean", "build")

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.9")
    providedCompile(group = "javax.servlet", name = "javax.servlet-api", version = "3.1.0")
    testImplementation(group = "org.testng", name = "testng", version = "[6.11]")
}

gretty {
    // supported values:
    // 'jetty7', 'jetty8', 'jetty9', 'jetty93', 'jetty94', 'tomcat7', 'tomcat8'
    servletContainer="tomcat9" //Use Jetty7 which is compatible with JDK6
    httpPort = 8080
    contextPath = "/gradle-war"
    springBoot = false
}

java{
    sourceCompatibility=JavaVersion.VERSION_1_8
    targetCompatibility=JavaVersion.VERSION_1_8
}

tasks.war {
    archiveFileName.set("gradle-war.war")
}

val test by tasks.existing(Test::class) {
    testLogging {
        // show standard out and standard error of the test JVM(s) on the console
        showStandardStreams = true
        exceptionFormat = FULL
    }
    useTestNG {
        excludeGroups("integration")
    }
}

val integrationTest by tasks.registering(Test::class) {
    dependsOn(test)
    description = "Runs the integration test suite."
    group = VERIFICATION_GROUP

    testLogging {
        // show standard out and standard error of the test JVM(s) on the console
        showStandardStreams = true
        exceptionFormat = FULL
    }
    useTestNG {
        includeGroups("integration")
    }
}
