package com.cuber.devfest.data

import com.cuber.devfest.data.model.Product
import io.reactivex.Single

interface ProductRepositoryImp {

    fun getProductById(productId: String): Single<Product>

    fun getProductList(): Single<List<Product>>
}