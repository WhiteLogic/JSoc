rootProject.name = "JSoc"
rootProject.buildFileName = "build.gradle.kts"

pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}

	val versions_kotlin           : String by settings
	val versions_spring_boot      : String by settings
	val versions_spring_dependency: String by settings
	val versions_docker_remote_api: String by settings
	val versions_buildtools       : String by settings
	val versions_sonarqube        : String by settings

	plugins {
		kotlin("jvm")                         version versions_kotlin
		kotlin("plugin.spring")               version versions_kotlin
		kotlin("plugin.jpa")                  version versions_kotlin
		kotlin("plugin.lombok")               version versions_kotlin
		id("org.springframework.boot")        version versions_spring_boot
		id("io.spring.dependency-management") version versions_spring_dependency
		id("com.bmuschko.docker-remote-api")  version versions_docker_remote_api
		id("org.graalvm.buildtools.native")   version versions_buildtools
		id("org.sonarqube")                   version versions_sonarqube
	}
}
