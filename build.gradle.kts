import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.gaveship"
version = "0.0.1-SNAPSHOT"

val springCloudDependenciesVersion by extra { "2021.0.1" }
val kotlinLoggingVersion by extra { "1.12.5" }

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.7.10"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
    java
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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")
    if (name == "employee-service"
        || name == "consumer-service") {
        apply(plugin = "org.springframework.boot")
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")

        dependencies {
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            implementation("io.github.microutils:kotlin-logging")
        }

        tasks.jar {
            enabled = true
        }
        tasks.forEach {
            if (it.name == "bootJar") {
                it.enabled = false
            }
        }
    }

    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudDependenciesVersion")
            }
            dependency("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
