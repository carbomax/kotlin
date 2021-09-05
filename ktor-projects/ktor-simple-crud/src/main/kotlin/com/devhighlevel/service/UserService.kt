package com.devhighlevel.service

import com.devhighlevel.dao.UserDao
import com.devhighlevel.domain.dto.request.UserDto
import com.devhighlevel.domain.dto.response.UserResponseDto
import com.devhighlevel.domain.entities.User
import com.devhighlevel.domain.exceptions.CrudException
import io.ktor.features.*
import software.amazon.awssdk.http.HttpStatusCode

class UserService(private val userDao: UserDao) {

    suspend fun save(userDto: UserDto): UserResponseDto {
        val user = userDao.save(User(userDto)) ?: throw CrudException(HttpStatusCode.BAD_REQUEST.toString(), "User not created")
        return UserResponseDto(user)
    }

    suspend fun update(userDto: UserDto, id: String): UserResponseDto{
        userDao.findById(id) ?: throw NotFoundException("User not found by id: $id")
        val userUpdated = userDao.update(User(userDto, id)) ?: throw CrudException(HttpStatusCode.BAD_REQUEST.toString(), "User not updated")
        return UserResponseDto(userUpdated)
    }

    suspend fun delete(id: String): UserResponseDto {
        val userDeleted = userDao.delete(id) ?: throw CrudException(HttpStatusCode.BAD_REQUEST.toString(), "User not deleted")
        return UserResponseDto(userDeleted)
    }

    suspend fun findAll(): List<UserResponseDto> {
        return userDao.findAll().map { UserResponseDto(it) }
    }
}