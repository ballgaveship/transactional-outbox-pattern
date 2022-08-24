import org.springframework.boot.gradle.tasks.bundling.BootJar

apply(plugin = "org.springframework.boot")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.awspring.cloud:spring-cloud-aws-messaging")
    implementation("io.awspring.cloud:spring-cloud-aws-autoconfigure")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging")

    implementation(project(":domain-events"))
}

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<BootJar> {
    enabled = true
}
