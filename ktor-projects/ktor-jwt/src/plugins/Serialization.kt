package com.devhighlevel.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*

fun Application.gsonSerialization() {

    install(ContentNegotiation){
        gson()
    }
}