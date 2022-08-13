package com.gaveship.employee.application

import com.gaveship.employee.domain.model.Employee
import com.gaveship.events.EmployeeCreated
import com.gaveship.events.EmployeeDeleted
import com.gaveship.events.EmployeeUpdated
import com.gaveship.outbox.domain.model.EnrichedDomainEvent

class EmployeeDomainEventMapper {
    private val employeeAggregateType = Employee::class.java.simpleName

    fun toEmployeeCreatedDomainEvent(employee: Employee): EnrichedDomainEvent<EmployeeCreated> {
        val id = employee.id
        val firstName = employee.firstName
        val lastName = employee.lastName
        val displayName = employee.displayName
        requireNotNull(id)
        requireNotNull(firstName)
        requireNotNull(lastName)
        requireNotNull(displayName)
        val employeeCreated = EmployeeCreated(
            id,
            firstName,
            lastName,
            displayName
        )
        return EnrichedDomainEvent(
            aggregateType = employeeAggregateType,
            aggregateId = id.toString(),
            domainEvent = employeeCreated
        )
    }

    fun toEmployeeUpdatedDomainEvent(employee: Employee): EnrichedDomainEvent<EmployeeUpdated> {
        val id = employee.id
        val firstName = employee.firstName
        val lastName = employee.lastName
        val displayName = employee.displayName
        requireNotNull(id)
        requireNotNull(firstName)
        requireNotNull(lastName)
        requireNotNull(displayName)
        val employeeUpdated = EmployeeUpdated(
            id,
            firstName,
            lastName,
            displayName
        )
        return EnrichedDomainEvent(
            aggregateType = employeeAggregateType,
            aggregateId = id.toString(),
            domainEvent = employeeUpdated
        )
    }

    fun toEmployeeDeletedDomainEvent(id: Long?): EnrichedDomainEvent<EmployeeDeleted> {
        requireNotNull(id)
        val employeeDeleted = EmployeeDeleted(id)
        return EnrichedDomainEvent(
            aggregateType = employeeAggregateType,
            aggregateId = id.toString(),
            domainEvent = employeeDeleted
        )
    }
}