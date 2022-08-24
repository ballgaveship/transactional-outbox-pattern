package com.gaveship.employee.application.impl

import com.gaveship.employee.application.EmployeeDomainEventMapper
import com.gaveship.employee.application.EmployeeNotFoundException
import com.gaveship.employee.application.EmployeeService
import com.gaveship.employee.domain.EmployeeRepository
import com.gaveship.employee.domain.model.Employee
import com.gaveship.employee.domain.model.UpdateEmployeeDto
import org.springframework.context.ApplicationEventPublisher
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class EmployeeServiceImpl(
    private val em: EntityManager,
    private val employeeRepository: EmployeeRepository,
    private val eventPublisher: ApplicationEventPublisher
) : EmployeeService {
    private val employeeDomainEventMapper = EmployeeDomainEventMapper()

    @Transactional
    override fun register(employee: Employee): Long {
        em.persist(employee)
        eventPublisher.publishEvent(employeeDomainEventMapper.toEmployeeCreatedDomainEvent(employee))
        return employee.id ?: throw IllegalStateException("id should not be null.")
    }

    @Transactional
    @Throws(EmployeeNotFoundException::class)
    override fun update(id: Long, updateEmployeeDto: UpdateEmployeeDto) {
        employeeRepository.findById(id)
            .map { existingEmployee: Employee ->
                existingEmployee.updateTo(updateEmployeeDto)
                eventPublisher.publishEvent(employeeDomainEventMapper.toEmployeeUpdatedDomainEvent(existingEmployee))
            }
            .orElseThrow { EmployeeNotFoundException(id) }
    }

    @Transactional
    @Throws(EmployeeNotFoundException::class)
    override fun delete(id: Long) {
        try {
            employeeRepository.deleteById(id)
            eventPublisher.publishEvent(employeeDomainEventMapper.toEmployeeDeletedDomainEvent(id))
        } catch (e: EmptyResultDataAccessException) {
            throw EmployeeNotFoundException(id)
        }
    }

    @Transactional(readOnly = true)
    @Throws(EmployeeNotFoundException::class)
    override fun find(id: Long): Employee {
        return employeeRepository.findById(id)
            .orElseThrow { EmployeeNotFoundException(id) }
    }

    @Transactional(readOnly = true)
    override fun list(): List<Employee> {
        return employeeRepository.findAll()
    }
}