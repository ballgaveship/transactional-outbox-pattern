dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations")
}

tasks {
    withType<Jar> {
        enabled = true
    }
}