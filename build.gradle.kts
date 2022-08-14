import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.gaveship"
version = "0.0.1-SNAPSHOT"

apply(from = "$rootDir/versions.gradle.kts")
val springCloudDependenciesVersion: String by extra
val kotlinLoggingVersion: String by extra
val awsSpringCloudVersion: String by extra
val hibernateTypesVersion: String by extra
val jacksonAnnotationsVersion: String by extra

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
    apply(from = "$rootDir/versions.gradle.kts")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")

    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudDependenciesVersion")
            }
            dependency("com.vladmihalcea:hibernate-types-52:$hibernateTypesVersion")
            dependency("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
            dependency("io.awspring.cloud:spring-cloud-aws-messaging:$awsSpringCloudVersion")
            dependency("io.awspring.cloud:spring-cloud-aws-autoconfigure:$awsSpringCloudVersion")
            dependency("com.fasterxml.jackson.core:jackson-annotations:$jacksonAnnotationsVersion")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
