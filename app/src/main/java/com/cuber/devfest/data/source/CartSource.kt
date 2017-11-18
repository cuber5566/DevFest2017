package com.cuber.devfest.data.source

import com.cuber.devfest.data.model.Product

interface CartSource {

    fun getCartPostList(): List<Product>

    fun isAddedCart(productId: String): Boolean

    fun addToCart(productId: String)

    fun removeFromCart(productId: String)
}