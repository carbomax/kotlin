package com.devhighlevel

import io.ktor.application.*
import com.devhighlevel.plugins.*

fun main(args: Array<String>): Unit {
    ModuleLoader.init()
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
}
