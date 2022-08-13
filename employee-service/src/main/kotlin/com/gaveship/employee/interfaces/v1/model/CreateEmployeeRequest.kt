package com.gaveship.employee.interfaces.v1.model

data class CreateEmployeeRequest(
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val dept: String? = "",
    val phoneNumber: String? = "",
)