package com.devhighlevel

import com.devhighlevel.plugins.configureSecurity
import com.devhighlevel.plugins.gsonSerialization
import com.devhighlevel.plugins.jwtAuthentication
import com.devhighlevel.routes.applicationRouting
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
   jwtAuthentication()
   gsonSerialization()
   configureSecurity()
   applicationRouting()
}

