package com.devhighlevel.plugins

import io.ktor.application.*
import io.ktor.auth.*

fun Application.jwtAuthentication(){
    install(Authentication){

    }
}