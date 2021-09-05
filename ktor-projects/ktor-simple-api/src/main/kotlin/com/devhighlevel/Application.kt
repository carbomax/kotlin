package com.devhighlevel

import io.ktor.application.*
import com.devhighlevel.plugins.*
import io.ktor.locations.*

fun main(args: Array<String>): Unit =
        io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(Locations)
    contentNegotiation()
    configureRouting()
}
