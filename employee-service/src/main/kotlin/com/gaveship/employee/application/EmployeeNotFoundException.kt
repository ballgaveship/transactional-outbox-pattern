package com.gaveship.employee.application

class EmployeeNotFoundException(override val message: String?) : RuntimeException(message)