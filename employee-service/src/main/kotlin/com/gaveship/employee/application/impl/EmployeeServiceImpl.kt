package com.gaveship.employee.application.impl

import com.gaveship.employee.application.EmployeeQuery
import com.gaveship.employee.application.EmployeeService
import com.gaveship.employee.domain.model.Employee
import com.gaveship.employee.domain.model.EmployeeRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Transactional
@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository
) : EmployeeService {
    override fun register(employee: Employee): Employee {
        return employeeRepository.save(employee)
    }

    override fun delete(id: Long) {
        employeeRepository.deleteById(id)
    }

    override fun find(id: Long): Employee {
        return employeeRepository.findById(id).get()
    }

    override fun list(query: EmployeeQuery): List<Employee> {
        TODO("Not yet implemented")
    }
}