package com.devhighlevel.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
fun Application.contentNegotiation(){
    install(ContentNegotiation){
       gson {
           setPrettyPrinting()
           disableHtmlEscaping()
       }
    }
}