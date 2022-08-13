package com.gaveship.events

data class EmployeeDeleted(
    val id: Long
) : DomainEvent()