import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version Versions.springDependencyManagement
    id ("org.jetbrains.kotlin.plugin.allopen") version Versions.kotlin
    id ("org.jetbrains.kotlin.plugin.noarg") version Versions.kotlin
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
    kotlin("plugin.jpa") version Versions.kotlin
}

noArg {
    annotation("javax.persistence.Entity")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "com.gaveship"
    version = "0.0.1-SNAPSHOT"
}

subprojects {
    apply(from = "$rootDir/versions.gradle.kts")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")

    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudDependencies}")
            }
            dependency("com.vladmihalcea:hibernate-types-52:${Versions.hibernateTypes}")
            dependency("io.github.microutils:kotlin-logging:${Versions.kotlinLogging}")
            dependency("io.awspring.cloud:spring-cloud-aws-messaging:${Versions.awsSpringCloud}")
            dependency("io.awspring.cloud:spring-cloud-aws-autoconfigure:${Versions.awsSpringCloud}")
            dependency("com.fasterxml.jackson.core:jackson-annotations:${Versions.jacksonAnnotations}")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
