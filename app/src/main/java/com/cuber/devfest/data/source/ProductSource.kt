package com.cuber.devfest.data.source

import com.cuber.devfest.data.model.Product
import io.reactivex.Single

interface ProductSource {

    fun getProductById(productId: String): Single<Product>

    fun getProductListBySeller(sellerId: String): Single<List<Product>>

    fun getProductListByCategory(categoryId: String): Single<List<Product>>
}