plugins {
	java
	id("org.springframework.boot") version "3.5.0-M2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    // base dependencies
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    
	// Build web, including RESTful, applications using Spring MVC
	// Uses Apache Tomcat as the default embedded container
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // Provides fast application restarts, LiveReload, and configurations
    developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Generate metadata for developers to offer contextual help and "code completion"
	// when working with custom configuration keys (ex.application.properties/.yml files)
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Java annotation library which helps to reduce boilerplate code
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    
	// Document RESTful services by combining hand-written with Asciidoctor
	// and auto-generated snippets produced with Spring MVC Test
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
