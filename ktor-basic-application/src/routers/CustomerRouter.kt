package com.devhighlevel.routers

import com.devhighlevel.models.Customer
import com.devhighlevel.services.CustomerService
import com.devhighlevel.services.customers
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.customerRouting() {
        route("/customers") {
            get {
                CustomerService()
                if(customers.isNotEmpty()) {
                    call.respond(customers)
                } else call.respondText ( "Customers not found", status = HttpStatusCode.NotFound )
            }

            get ("{id}" ) {

                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val customer = customers.find { it.id == id } ?: return@get call.respondText(
                        "No customer with id $id",
                        status = HttpStatusCode.NotFound
                    )
                call.respond(customer)
            }

            post {
                val customer = call.receive<Customer>()
                customers.add(customer)
                call.respondText("Customer stored correctly", status = HttpStatusCode.Accepted)
            }

            delete ("{id}"){

                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

                if(customers.removeIf{ it.id == id }){
                    call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("Not Found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }


    fun Application.customersRoutes(){
       routing {
           customerRouting()
       }
    }
