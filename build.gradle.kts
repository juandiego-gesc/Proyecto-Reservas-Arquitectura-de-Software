plugins {
	java
	id("org.springframework.boot") version "2.7.14"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	jacoco
}

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

}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks.test{
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport{

	dependsOn(tasks.test)

	reports{

		csv.required.set(true)

	}
}

jacoco{

	toolVersion="0.8.8"

}