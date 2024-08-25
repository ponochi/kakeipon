plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.panda.systems.kakeipon"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
//        extendsFrom annotationProcessor
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

dependencies {
    implementation("org.springframework:spring-core")
    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.postgresql", "postgresql", "42.7.3")
    implementation("org.springframework.session:spring-session-core")
    implementation("org.springframework.session:spring-session-jdbc")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:10.1.28")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:10.1.28")
    implementation("org.apache.tomcat:tomcat-jasper:10.1.28")
    implementation("org.apache.tomcat:tomcat-jasper-el:10.1.28")
    implementation("org.apache.tomcat:tomcat-jsp-api:10.1.28")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
