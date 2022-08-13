package com.gaveship.employee.interfaces.v1.model

data class UpdateEmployeeRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val displayName: String? = null
)