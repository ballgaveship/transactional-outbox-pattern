package com.gaveship.employee.domain

import com.gaveship.employee.domain.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>