package com.example.domain.order.service


import com.example.domain.BusinessException
import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import com.example.domain.order.repository.OrderRepository
import java.util.*

class DomainOrderService(private val orderRepository: OrderRepository): OrderService {

    override fun createOrder(client: Client): Order? {
       return orderRepository.save(Order(UUID.randomUUID(), client) )
    }

    override fun addProduct(uuid: UUID, product: Product, quantity: Int) {
        val orderFounded = orderRepository.findById(uuid) ?: throw BusinessException("Order not founded by id: $uuid")
        orderFounded.addProduct(product, quantity)
        orderRepository.save(orderFounded)
    }
}