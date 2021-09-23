package com.example.domain.order.service

import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import java.util.*

interface OrderService {
    fun createOrder(client: Client) : Order?
    fun addProduct(uuid: UUID, product: Product, quantity: Int = 1)
}