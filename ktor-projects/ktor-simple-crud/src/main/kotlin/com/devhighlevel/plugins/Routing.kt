package com.devhighlevel.plugins

import com.devhighlevel.routes.userRoutes
import com.devhighlevel.service.UserService
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }

    routing {
        route("/users"){
            userRoutes()
        }
    }

    statusPages()
}
