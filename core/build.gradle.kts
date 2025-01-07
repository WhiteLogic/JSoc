plugins {
    `java-library`
    id("org.sonarqube")
    jacoco
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:${property("versions.jackson_databind")}")

    implementation         ("org.projectlombok:lombok:${property("versions.lombok")}")
    annotationProcessor    ("org.projectlombok:lombok:${property("versions.lombok")}")
    testImplementation     ("org.projectlombok:lombok:${property("versions.lombok")}")
    testAnnotationProcessor("org.projectlombok:lombok:${property("versions.lombok")}")

    testImplementation("ch.qos.logback:logback-core:${property("versions.logback")}")
    testImplementation("ch.qos.logback:logback-classic:${property("versions.logback")}")

    testImplementation("org.slf4j:slf4j-api:${property("versions.slf4j")}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${property("versions.junit")}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${property("versions.junit")}")
    testImplementation("org.mockito:mockito-core:${property("versions.mockito")}")
    testImplementation("org.mockito:mockito-junit-jupiter:${property("versions.mockito")}")
}

tasks.withType<Jar> {
    manifest {
        attributes["Built-By"] = "WhiteLogic"
        attributes["Implementation-Version"] = archiveVersion
    }
}

tasks.withType<Test> {
    enabled = true
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
        csv.required = false
    }
}

sonar {
    properties {
        property("sonar.projectKey", "JSoc")
        property("sonar.login", "sqp_tokenExample")
        property("sonar.qualitygate.wait", "${property("sonar.qualitygate.wait")}")
        property("sonar.host.url", "${property("sonar.host.url")}")
    }
}
