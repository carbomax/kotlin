package com.jetbrains.handson.chat.server

import com.jetbrains.handson.chat.server.routes.routes
import com.jetbrains.handson.chat.server.routes.server
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.util.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(WebSockets)
    routes()
}
