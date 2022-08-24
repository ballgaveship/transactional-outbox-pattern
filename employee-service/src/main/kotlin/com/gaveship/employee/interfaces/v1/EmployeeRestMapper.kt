package com.gaveship.employee.interfaces.v1

import com.gaveship.employee.domain.model.Employee
import com.gaveship.employee.interfaces.v1.model.CreateEmployeeRequest
import com.gaveship.employee.interfaces.v1.model.GetEmployeeResponse

class EmployeeRestMapper {
    fun toGetEmployeeResponse(employeeList: List<Employee>): GetEmployeeResponse =
        GetEmployeeResponse(
            employeeList.size,
            employeeList
        )

    fun toEmployee(request: CreateEmployeeRequest): Employee =
        Employee(
            username = request.username,
            email = request.email,
            firstName = request.firstName,
            lastName = request.lastName,
            displayName = request.displayName,
            dept = request.dept,
            phoneNumber = request.phoneNumber
        )
}