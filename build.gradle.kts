import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("info.solidsoft.pitest") version "1.9.0"
    jacoco
    kotlin("jvm") version "1.7.10"
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

    implementation ("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation(kotlin("stdlib-jdk8"))



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
                    exclude("com/farjuce/appreservas/controller/dto/",
                            "com/farjuce/appreservas/bd", "com/farjuce/appreservas/logica/exception")
                }
            })
    )

    reports {
        csv.required.set(true)
    }
}

pitest {
    junit5PluginVersion = "1.0.0"
    excludedClasses = setOf("com.farjuce.appreservas.controller.dto.**",
            "com.farjuce.appreservas.bd.**")
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}