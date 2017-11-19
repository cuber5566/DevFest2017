package com.cuber.devfest.data

import com.cuber.devfest.data.model.Product
import io.reactivex.Single

interface ProductRepo {

    fun getProductById(productId: String): Single<Product>

    fun getProductListBySeller(sellerId: String): Single<List<Product>>

    fun getProductListByCategory(categoryId: String): Single<List<Product>>

    fun getCartPostList(): List<Product>

    fun isAddedCart(productId: String): Boolean

    fun addToCart(product: Product)

    fun removeFromCart(product: Product)
}