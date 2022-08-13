package com.gaveship.employee.application

class EmployeeNotFoundException(id: Long) :
    RuntimeException(String.format("Employee with %s does not exist", id.toString()))