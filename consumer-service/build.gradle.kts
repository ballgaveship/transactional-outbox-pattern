dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.awspring.cloud:spring-cloud-aws-messaging:2.4.2")
    implementation("io.awspring.cloud:spring-cloud-aws-autoconfigure:2.4.2")

    implementation(project(":domain-events"))
}