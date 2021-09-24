package com.example.infraestructure.repository

import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import com.example.domain.order.repository.OrderRepository
import java.util.*

class DynamoRepository: OrderRepository {

    private var orders = mutableMapOf<UUID, Order>()

    override fun findById(id: UUID): Order? {
        return orders[id]
    }

    override fun findProductById(productId: UUID): Product? {
        for (order in orders) {
            return order.value.items().find { it.product.id == productId }?.product
        }

        return null
    }

    override fun findCustomerById(customerId: UUID): Client? {
        for (order in orders) {
            if (order.value.client().id == customerId) {
                return order.value.client()
            }
        }

        return null
    }

    override fun save(order: Order): Order? {
        return orders.put(order.id(), order)
    }
}