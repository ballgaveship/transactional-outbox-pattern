rootProject.name = "transactional-outbox-pattern"

include("employee-service")
include("consumer-service")
include("domain-events")

project(":employee-service").projectDir = File("employee-service")
project(":consumer-service").projectDir = File("consumer-service")
project(":domain-events").projectDir = File("domain-events")
