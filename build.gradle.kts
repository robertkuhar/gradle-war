import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.language.base.plugins.LifecycleBasePlugin.VERIFICATION_GROUP

plugins {
    java
    eclipse
    idea
    war
    id("org.gretty") version "4.0.3"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

version = "1.1"

defaultTasks("clean", "build")

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    providedCompile("jakarta.servlet:jakarta.servlet-api:5.0.0")
    testImplementation("org.testng:testng:7.7.0")
}

gretty {
    servletContainer="jetty11"
    httpPort = 8080
    contextPath = "/gradle-war"
    springBoot = false
}

java{
    sourceCompatibility=JavaVersion.VERSION_11
    targetCompatibility=JavaVersion.VERSION_11
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

tasks.check{
    dependsOn(integrationTest)
}
