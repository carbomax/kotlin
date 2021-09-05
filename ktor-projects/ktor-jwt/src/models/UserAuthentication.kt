package com.devhighlevel.models

import io.ktor.auth.*

data class UserAuthentication(val userName: String, val other: String = "default") : Principal