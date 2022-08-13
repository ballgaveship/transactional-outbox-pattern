package com.gaveship.outbox.domain

import com.gaveship.outbox.domain.model.Outbox
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

@Repository
interface OutboxRepository : JpaRepository<Outbox, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findAllByOrderByIdAsc(pageable: Pageable): Page<Outbox>
}