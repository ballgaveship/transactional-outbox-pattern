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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Enumerated(EnumType.STRING)
    var status: Status = Status.ACTIVE,
    var username: String = "",
    var email: String = "",
    var firstName: String? = null,
    var lastName: String? = null,
    var displayName: String? = null,
    var dept: String? = null,
    var phoneNumber: String? = null,
    @Column(updatable = false)
    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN,
    @Column(insertable = false)
    @LastModifiedDate
    var modifiedDate: LocalDateTime? = null
) {
    fun updateTo(employee: Employee) {
        if (employee.firstName != null) {
            this.firstName = employee.firstName
        }
        if (employee.lastName != null) {
            this.lastName = employee.lastName
        }
        if (employee.displayName != null) {
            this.displayName = employee.displayName
        }
    }

    enum class Status {
        ACTIVE, INACTIVE
    }
}