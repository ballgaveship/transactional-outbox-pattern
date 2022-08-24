package com.gaveship.employee.interfaces.v1

import com.gaveship.employee.application.EmployeeService
import com.gaveship.employee.domain.model.UpdateEmployeeDto
import com.gaveship.employee.interfaces.v1.model.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {
    private val employeeRestMapper: EmployeeRestMapper = EmployeeRestMapper()

    @GetMapping
    fun getEmployee(): GetEmployeeResponse {
        val employeeList = employeeService.list()
        return employeeRestMapper.toGetEmployeeResponse(employeeList)
    }

    @PostMapping
    fun createEmployee(
        @Valid @RequestBody(required = true) createEmployeeRequest: CreateEmployeeRequest
    ): CreateEmployeeResponse {
        val employeeId = employeeService.register(employeeRestMapper.toEmployee(createEmployeeRequest))
        return CreateEmployeeResponse(employeeId)
    }

    @PatchMapping("/{employeeId}")
    fun updateEmployee(
        @PathVariable employeeId: Long,
        @Valid @RequestBody(required = true) updateEmployeeRequest: UpdateEmployeeRequest
    ): UpdateEmployeeResponse {
        employeeService.update(
            employeeId,
            UpdateEmployeeDto(updateEmployeeRequest.firstName, updateEmployeeRequest.lastName, updateEmployeeRequest.displayName)
        )
        return UpdateEmployeeResponse(employeeId)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    fun deleteEmployee(
        @PathVariable employeeId: Long
    ): Unit = employeeService.delete(employeeId)
}