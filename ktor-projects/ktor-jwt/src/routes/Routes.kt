package com.devhighlevel.routes

import com.devhighlevel.models.UserAuthentication
import com.devhighlevel.plugins.generateToken
import com.devhighlevel.plugins.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticationRouting() {

    post ("/auth/generate-token"){
        val user = call.receive<UserAuthentication>()
        val token = generateToken(user)
        call.respond(token)
    }

    authenticate{
        get("/authenticate"){
            call.respond("get authenticated value from token " +
                    "name = ${call.user?.userName}")
        }
    }
}


fun Application.applicationRouting() {
    routing {
        authenticationRouting()
    }
}


