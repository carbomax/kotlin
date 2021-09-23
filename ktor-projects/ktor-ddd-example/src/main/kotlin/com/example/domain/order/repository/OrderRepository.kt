package com.example.domain.order.repository

import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import java.util.*

interface OrderRepository {
    fun findById(id: UUID) : Order
    fun findProductById(productId: UUID): Product
    fun findCustomerById(customerId: UUID): Client
    fun save(order: Order): Order?
}