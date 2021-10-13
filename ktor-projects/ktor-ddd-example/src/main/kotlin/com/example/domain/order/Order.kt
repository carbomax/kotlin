package com.example.domain.order

import com.example.domain.BusinessException
import com.example.domain.client.Client
import java.util.*

class Order(val id: UUID, val client: Client) {

    private val items = mutableListOf<Item>()

    val paid: Boolean = false

    fun items() = items.toList()

    fun addProduct(product: Product, quantity: Int) {
        validateExistProduct(product)
        items.add(Item(product, quantity))
    }

    fun changeProductQuantity(product: Product, quantity: Int) {
        validateIfProductIsOnList(product)
        val itemFounded = items.first { it.product == product }
        itemFounded.changeQuantity(quantity)
    }

    fun removeProduct(product: Product) {
        validateIfProductIsOnList(product)
        items.removeAll { it.product == product }
    }

    private fun validateIfProductIsOnList(product: Product) {
        val isOnList = items.any { it.product == product }
        if (!isOnList) throw BusinessException("The product isn't included in this order")
    }

    private fun validateExistProduct(product: Product) {
        if (this.items.any { it.product == product }) {
            throw BusinessException("Product [$product] already exist")
        }
    }
}