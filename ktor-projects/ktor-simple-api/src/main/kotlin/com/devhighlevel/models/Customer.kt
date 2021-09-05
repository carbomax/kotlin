package com.devhighlevel.models

import java.util.*

var customerStorage = mutableListOf<Customer>()

data class Customer(val id: String? = UUID.randomUUID().toString(), var firstName: String, var lastName: String, var email: String)
