package com.example.infraestructure.repository

import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import com.example.domain.order.repository.OrderRepository
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.util.*

class DynamoRepository: OrderRepository {

    override fun findById(id: UUID): Order {
        TODO("Not yet implemented")
    }

    override fun findProductById(productId: UUID): Product {
        TODO("Not yet implemented")
    }

    override fun findCustomerById(customerId: UUID): Client {
        TODO("Not yet implemented")
    }

    override fun save(order: Order): Order? {
        TODO("Not yet implemented")
    }
}