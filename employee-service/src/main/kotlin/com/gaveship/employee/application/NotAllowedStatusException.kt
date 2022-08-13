package com.gaveship.employee.application

class NotAllowedStatusException(override val message: String?) : RuntimeException(message)