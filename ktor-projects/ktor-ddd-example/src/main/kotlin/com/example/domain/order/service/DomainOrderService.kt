package com.example.domain.order.service


import com.example.domain.BusinessException
import com.example.domain.order.Order
import com.example.domain.order.Product
import com.example.domain.client.Client
import com.example.domain.order.repository.OrderRepository
import java.util.*

class DomainOrderService(private val orderRepository: OrderRepository): OrderService {

    override fun createOrder(client: Client): Order? {
       return orderRepository.save(Order(UUID.randomUUID(), client))
    }

    override fun addProduct(uuid: UUID, product: Product, quantity: Int) {
        val orderFound = orderRepository.findById(uuid) ?: throw BusinessException("Order not found by id: $uuid")
        orderFound.addProduct(product, quantity)
        orderRepository.save(orderFound)
    }

    override fun getOrder(orderID: UUID): Order? {
        return orderRepository.findById(orderID) ?: throw BusinessException("Order not found by id: $orderID")
    }
}