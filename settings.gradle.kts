rootProject.name = "transactional-outbox-pattern"

include("employee-service")
include("domain-events")

project(":employee-service").projectDir = File("employee-service")
project(":domain-events").projectDir = File("domain-events")
