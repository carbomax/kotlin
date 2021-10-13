package com.example.domain

data class BusinessException(override var message: String): Exception(message)
