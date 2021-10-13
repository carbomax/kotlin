package com.example.application.order

import java.lang.RuntimeException

class OrderHandlerException(override val message: String): RuntimeException(message)