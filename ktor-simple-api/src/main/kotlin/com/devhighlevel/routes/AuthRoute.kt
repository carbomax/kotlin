package com.devhighlevel.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.signup(){

    post("/signup") {
        val register = call.receiveParameters()
        call.respondText("User with => email: ( ${register["email"]} ) password: ( ${register["password"]} )")
    }
}