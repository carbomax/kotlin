package com.example.application.order

import com.example.domain.order.Product
import com.example.domain.order.client.Client
import com.example.domain.order.service.OrderService
import com.example.shared.request.ClientRequest
import com.example.shared.respond.OrderResponse
import com.example.shared.request.ProductRequest
import java.util.*

class OrderCatalogue(private val orderService: OrderService) {

     fun createOrder(clientRequest: ClientRequest): OrderResponse {
        val orderCreated = orderService.createOrder(Client(UUID.fromString(clientRequest.id), clientRequest.name))
                          ?: throw OrderCatalogueException("Order not created")
       return OrderResponse(orderCreated)
    }

    fun addProduct(orderId: String?, productRequest: ProductRequest) {
        try {
            orderService.addProduct(UUID.fromString(orderId), Product(productRequest.id, productRequest.description, productRequest.unitPrice))
        }catch (ex: Exception){
            throw OrderCatalogueException("Product not added to order with orderId: $orderId")
        }
    }
}