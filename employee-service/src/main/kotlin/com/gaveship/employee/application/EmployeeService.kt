package com.gaveship.employee.application

import com.gaveship.employee.domain.model.Employee

interface EmployeeService {
    fun register(employee: Employee): Employee
    fun delete(id: Long)
    fun find(id: Long): Employee
    fun list(query: EmployeeQuery): List<Employee>
}