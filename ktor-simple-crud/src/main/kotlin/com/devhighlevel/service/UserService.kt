package com.devhighlevel.service

import com.devhighlevel.dao.UserDao
import com.devhighlevel.domain.dto.request.UserDto
import com.devhighlevel.domain.dto.response.UserResponseDto
import com.devhighlevel.domain.entities.User
import com.devhighlevel.domain.exceptions.CrudException
import software.amazon.awssdk.http.HttpStatusCode

class UserService(private val userDao: UserDao) {

   suspend fun save(userDto: UserDto): UserResponseDto {
       val user = userDao.save(User(userDto)) ?: throw CrudException(HttpStatusCode.BAD_REQUEST.toString(), "User not created")
        return UserResponseDto(user)
    }
}