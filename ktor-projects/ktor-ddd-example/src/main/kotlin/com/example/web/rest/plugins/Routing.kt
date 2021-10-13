package com.example.web.rest.plugins

import com.example.web.rest.orders
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        orders()
    }

}
