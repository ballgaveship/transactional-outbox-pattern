mapOf(
    "springCloudDependenciesVersion" to "2021.0.1",
    "kotlinLoggingVersion" to "1.12.5",
    "hibernateTypesVersion" to "2.11.1",
    "awsSpringCloudVersion" to "2.4.2",
    "jacksonAnnotationsVersion" to "2.13.3",
).forEach { (name, version) ->
    project.extra.set(name, version)
}