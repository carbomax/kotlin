package com.devhighlevel.routes

import com.devhighlevel.domain.dto.request.UserDto
import com.devhighlevel.service.UserService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {

    val userService: UserService by inject()

    post {
        val userDto = call.receive<UserDto>()
        call.respond(userService.save(userDto))
    }
}