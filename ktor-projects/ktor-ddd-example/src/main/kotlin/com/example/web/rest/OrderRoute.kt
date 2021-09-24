package com.example.web.rest

import com.example.shared.request.ClientRequest
import com.example.application.order.OrderCatalogue
import com.example.shared.request.ProductRequest
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Route.orders() {

            val orderCatalogue: OrderCatalogue by inject()

            get("/{id}") {
                val orderId: String? = call.parameters["id"]
                if (orderId == null) {
                    call.respond(HttpStatusCode.BadRequest, "order id is required")
                }
                val client = call.receive<ClientRequest>()
                call.respond(HttpStatusCode.Created, orderCatalogue.createOrder(client))

            }

            put("/{id}/") {
                val orderID: String? = call.parameters["id"]
                if (orderID == null) {
                    call.respond(HttpStatusCode.BadRequest, "order id is required")
                }
                val product = call.receive<ProductRequest>()
                orderCatalogue.addProduct(orderID, product)
                call.respond(HttpStatusCode.OK, product)
            }
        }
