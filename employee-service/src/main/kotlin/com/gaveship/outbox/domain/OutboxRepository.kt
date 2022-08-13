package com.gaveship.outbox.domain

import com.gaveship.outbox.domain.model.Outbox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OutboxRepository : JpaRepository<Outbox, Long>