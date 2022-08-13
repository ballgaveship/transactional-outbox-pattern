package com.gaveship.events

data class EmployeeCreated(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val displayName: String
) : DomainEvent()