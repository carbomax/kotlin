package com.devhighlevel.plugins

import com.devhighlevel.routes.customerRoutes
import com.devhighlevel.routes.signup
import com.devhighlevel.routes.upload
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {


    // Login and Register
    routing {
        signup()
    }

    // Upload
    routing {

        static("upload") {

        }
        route("/upload"){
            upload()
        }
    }

    // Hello World !!!
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }

    // Customer Crud in memory
    routing {
        route("/customers"){
            customerRoutes()
        }
    }

    // Handler exceptions
    statusPages()
}
