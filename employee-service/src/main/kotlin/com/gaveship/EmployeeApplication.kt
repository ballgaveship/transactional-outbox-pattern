package com.gaveship

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class EmployeeApplication

fun main(args: Array<String>) {
    runApplication<EmployeeApplication>(*args)
}
