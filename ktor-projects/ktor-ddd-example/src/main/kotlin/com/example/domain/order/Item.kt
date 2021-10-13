package com.example.domain.order

import com.example.domain.BusinessException

class Item(
    val product: Product,
    var quantity: Int,
) {
    init {
        validateQuantity(quantity)
    }

    fun changeQuantity(quantity: Int) {
        validateQuantity(quantity)
        this.quantity = quantity
    }

    private fun validateQuantity(quantity: Int) {
        if( quantity <= 0 ) {
            throw BusinessException("Quantity must be greater than zero")
        }
    }
}