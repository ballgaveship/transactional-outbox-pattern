dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.awspring.cloud:spring-cloud-aws-messaging:2.4.2")
    implementation("io.awspring.cloud:spring-cloud-aws-autoconfigure:2.4.2")
    implementation("com.vladmihalcea:hibernate-types-52:2.11.1")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("com.h2database:h2")

    implementation(project(":domain-events"))
}