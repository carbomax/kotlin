package com.devhighlevel.routes

import com.devhighlevel.models.Customer
import com.devhighlevel.models.customerStorage
import com.devhighlevel.routes.locations.CustomerLocation
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.get


fun Route.customerRoutes() {

    get {
            call.respond(mapOf("customers" to customerStorage))
    }

    get<CustomerLocation> { customer ->
        val id = customer.id ?: throw NotFoundException("Missing id parameter")
        println("Query value => ${customer.queryValue}")
        val customerFounded = customerStorage.find { it.id == id }
            ?: throw NotFoundException("Customer with id:$id not exist")
        call.respond(customerFounded)
    }

    post {
        val customer = call.receive<Customer>()
        val customerToSave =
            Customer(firstName = customer.firstName, lastName = customer.lastName, email = customer.email)
        customerStorage.add(customerToSave)
        call.respond(HttpStatusCode.Created, customerToSave)
    }

    put("{id}") {

        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing id parameter")
        val customer = call.receive<Customer>()
        val customerToUpdate = customerStorage.find { it.id == id }
            ?: return@put call.respond(HttpStatusCode.NotFound, "Customer with id:$id not exist")
        customerToUpdate.email = customer.email
        customerToUpdate.firstName = customer.firstName
        customerToUpdate.lastName = customer.lastName
        customerStorage = customerStorage.map {
            if (it.id == customerToUpdate.id) {
                return@map customerToUpdate
            } else return@map it
        } as MutableList<Customer>
        call.respond(customerToUpdate)
    }

    delete("{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing id parameter")
        customerStorage.removeIf { it.id == id }
        call.respond(HttpStatusCode.NoContent, "Customer deleted")
    }
}