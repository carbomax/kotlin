package com.devhighlevel.domain.exceptions

import java.lang.RuntimeException

class CrudException(
    val code: String,
    override val message: String
): RuntimeException(message)