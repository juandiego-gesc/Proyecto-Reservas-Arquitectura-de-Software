plugins {
    java
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("info.solidsoft.pitest") version "1.9.0"
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
}

group = "beyond.gilded.rose"
group = "com.farjuce"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("org.modelmapper:modelmapper:2.1.1")
    testImplementation("com.h2database:h2:2.2.220")
    compileOnly("org.projectlombok:lombok")
    implementation("mysql:mysql-connector-java:8.0.32")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.slf4j:slf4j-api:1.7.32")

    implementation("io.springfox:springfox-boot-starter:3.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {

    dependsOn(tasks.test)

    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude(
                    "com/farjuce/appreservas/controller/dto/",
                    "com/farjuce/appreservas/bd",
                    "com/farjuce/appreservas/logica/exception",
                    "com/farjuce/appreservas/AppreservasApplication.class",
                )
            }
        })
    )

    reports {
        csv.required.set(true)
        xml.required.set(true)
    }
    sonarqube {
        properties {
            property("sonar.projectName", "appreservas")
            property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        }
    }


}

pitest {
    junit5PluginVersion = "1.0.0"
    excludedClasses = setOf(
        "com.farjuce.appreservas.controller.dto.**",
        "com.farjuce.appreservas.bd.**",
        "com.farjuce.appreservas.AppreservasApplication.java"
    )
}

sonarqube {
    properties {
        property("sonar.projectName", "appreservas")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
