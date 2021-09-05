package com.devhighlevel.routes

import com.devhighlevel.domain.dto.request.UserDto
import com.devhighlevel.service.UserService
import io.ktor.application.*
import io.ktor.features.*
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

    put("/{id}") {
        val id = call.parameters["id"] ?: throw NotFoundException("Not exist parameter [ id ]")
        val userDto = call.receive<UserDto>()
        call.respond(userService.update(userDto, id))
    }

    delete("/{id}") {
        val id = call.parameters["id"] ?: throw NotFoundException("Not exist parameter [ id ]")
        call.respond(userService.delete(id))
    }

    get {
        call.respond(userService.findAll())
    }
}