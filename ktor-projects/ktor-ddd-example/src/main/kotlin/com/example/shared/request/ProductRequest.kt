package com.example.shared.request

import java.util.*

data class ProductRequest(
    val id: UUID,
    val description: String,
    val unitPrice: Double,
)