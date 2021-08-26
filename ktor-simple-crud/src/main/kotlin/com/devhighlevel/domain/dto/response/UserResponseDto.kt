package com.devhighlevel.domain.dto.response

import com.devhighlevel.domain.entities.User

data class UserResponseDto(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null
){
    constructor(userDb: User): this(
        userDb.pk,
        userDb.email,
        userDb.name
    )
}