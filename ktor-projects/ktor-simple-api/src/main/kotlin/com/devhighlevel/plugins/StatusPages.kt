package com.devhighlevel.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*


fun Application.statusPages() {
    install(StatusPages) {

        exception<NotFoundException> { cause ->
            respond(HttpStatusCode.BadRequest, cause)
        }
        exception<Exception> { cause ->
            respond( HttpStatusCode.InternalServerError, cause)
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respond(
    httpStatusCode: HttpStatusCode,
    cause: Throwable
) {
    call.respond(httpStatusCode, cause.message ?: httpStatusCode.description)
}