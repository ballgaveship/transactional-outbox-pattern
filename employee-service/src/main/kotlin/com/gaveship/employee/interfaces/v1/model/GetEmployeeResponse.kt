package com.gaveship.employee.interfaces.v1.model

import com.gaveship.employee.domain.model.Employee

data class GetEmployeeResponse(
    val totalCount: Int,
    val result: List<Employee>
)