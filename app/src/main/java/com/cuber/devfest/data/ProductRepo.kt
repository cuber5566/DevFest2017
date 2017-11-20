package com.cuber.devfest.data

import com.cuber.devfest.data.model.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepo {

    fun getProductById(productId: String): Single<Product>

    fun getProductListBySeller(sellerId: String): Single<List<Product>>

    fun getProductListByCategory(categoryId: String): Single<List<Product>>

    fun getCartPostList(): Single<List<Product>>

    fun addToCart(product: Product): Completable

    fun removeFromCart(product: Product): Completable
}