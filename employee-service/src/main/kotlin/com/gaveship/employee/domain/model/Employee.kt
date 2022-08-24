package com.gaveship.employee.domain.model

import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
class Employee(
    username: String,
    email: String,
    firstName: String? = null,
    lastName: String? = null,
    displayName: String? = null,
    dept: String? = null,
    phoneNumber: String? = null,
) {
    constructor() : this(
        username = "",
        email = ""
    )

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Enumerated(EnumType.STRING)
    var status: Status = Status.ACTIVE
        private set
    var username: String = username
        private set
    @Column(nullable = true)
    var email: String? = email
        private set
    @Column(nullable = true)
    var firstName: String? = firstName
        private set
    @Column(nullable = true)
    var lastName: String? = lastName
        private set
    @Column(nullable = true)
    var displayName: String? = displayName
        private set
    @Column(nullable = true)
    var dept: String? = dept
        private set
    @Column(nullable = true)
    var phoneNumber: String? = phoneNumber
        private set
    @Column(updatable = false)
    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN
        private set
    @Column(insertable = false, nullable = true)
    @LastModifiedDate
    var modifiedDate: LocalDateTime? = null
        private set

    fun updateTo(updateEmployeeDto: UpdateEmployeeDto) {
        if (updateEmployeeDto.firstName != null) {
            this.firstName = updateEmployeeDto.firstName
        }
        if (updateEmployeeDto.lastName != null) {
            this.lastName = updateEmployeeDto.lastName
        }
        if (updateEmployeeDto.displayName != null) {
            this.displayName = updateEmployeeDto.displayName
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    enum class Status {
        ACTIVE, INACTIVE
    }
}