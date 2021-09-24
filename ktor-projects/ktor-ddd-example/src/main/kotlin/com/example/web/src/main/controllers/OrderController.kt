package com.example.web.src.main.controllers

import com.example.domain.BusinessException
import com.example.domain.order.Product
import com.example.domain.order.client.Client
import com.example.domain.order.service.OrderService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.util.*

class OrderController(
    private val orderService: OrderService
) {

    companion object {
        fun Route.orders() {
            val orderController: OrderController by this.inject()

            get("orders/{id}") {
                val orderID: String? = call.parameters["id"]
                if (orderID == null) {
                    call.respond(HttpStatusCode.BadRequest, "order id is required")
                }
                try {
                    call.respond(orderController.orderService.getOrder(UUID.fromString(orderID)) as Any)
                } catch (be : BusinessException) {
                    call.respond(HttpStatusCode.NotFound, be.message)
                }

            }

            post("orders") {
                val client = call.receive<Client>()
                try {
                    call.respond(orderController.orderService.createOrder(client) as Any)
                } catch (be : BusinessException) {
                    call.respond(HttpStatusCode.InternalServerError, be.message)
                }
            }

            put("orders/{id}") {
                val orderID: String? = call.parameters["id"]
                if (orderID == null) {
                    call.respond(HttpStatusCode.BadRequest, "order id is required")
                }

                val product = call.receive<Product>()
                try {
                    call.respond(orderController.orderService.addProduct(UUID.fromString(orderID), product))
                } catch (be : BusinessException) {
                    call.respond(HttpStatusCode.InternalServerError, be.message)
                }
            }
        }
    }
}