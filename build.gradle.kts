plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.panda.systems.kakeipon"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
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
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.3")
    implementation("org.springframework.security:spring-security-core:6.3.3")
    implementation("org.springframework.security:spring-security-web:6.3.3")
    implementation("org.springframework.security:spring-security-config:6.3.3")
    implementation("org.springframework.security:spring-security-taglibs:6.3.3")
    implementation("org.springframework.security:spring-security-crypto:6.3.3")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.1.2.RELEASE")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
