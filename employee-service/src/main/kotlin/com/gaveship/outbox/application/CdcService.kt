package com.gaveship.outbox.application

interface CdcService {
    fun forwardEvent()
}