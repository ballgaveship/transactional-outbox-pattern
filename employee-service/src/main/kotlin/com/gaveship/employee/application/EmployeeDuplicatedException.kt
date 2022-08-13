package com.gaveship.employee.application

class EmployeeDuplicatedException(override val message: String?) : RuntimeException(message)