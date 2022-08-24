package com.gaveship.employee.domain.model

data class UpdateEmployeeDto(
    val firstName: String? = null,
    val lastName: String? = null,
    val displayName: String? = null
)