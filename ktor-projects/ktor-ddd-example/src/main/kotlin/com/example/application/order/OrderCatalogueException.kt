package com.example.application.order

import java.lang.RuntimeException

class OrderCatalogueException(override val message: String): RuntimeException(message)