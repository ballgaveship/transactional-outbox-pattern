package com.gaveship.employee.application

import com.gaveship.employee.domain.model.Employee
import com.gaveship.employee.domain.model.UpdateEmployeeDto

interface EmployeeService {
    fun register(employee: Employee): Long
    fun update(id: Long, updateEmployeeDto: UpdateEmployeeDto)
    fun delete(id: Long)
    fun find(id: Long): Employee
    fun list(): List<Employee>
}