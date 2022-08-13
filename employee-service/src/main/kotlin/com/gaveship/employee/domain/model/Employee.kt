package com.gaveship.employee.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Employee(
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @get:Enumerated(EnumType.STRING)
    var status: Status = Status.ACTIVE,
    var username: String = "",
    var email: String = "",
    var firstName: String? = null,
    var lastName: String? = null,
    var displayName: String? = null,
    var dept: String? = null,
    var phoneNumber: String? = null,
    @get:Column(updatable = false)
    @get:CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN,
    @get:Column(insertable = false)
    @get:LastModifiedDate
    var modifiedDate: LocalDateTime? = null
) {
    enum class Status {
        ACTIVE, INACTIVE
    }
}