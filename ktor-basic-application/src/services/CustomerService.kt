package com.devhighlevel.services

import com.devhighlevel.models.Customer

val customers = mutableListOf<Customer>()

class CustomerService() {

    init {
        if(customers.isEmpty()) {
            customers.add(Customer("customer1", "Luis", "Angel", "email@email.com"))
        }
    }
}

