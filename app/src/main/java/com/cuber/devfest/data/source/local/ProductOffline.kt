package com.cuber.devfest.data.source.local

import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.dao.ProductDao

class ProductOffline(private var productDao: ProductDao) {

    fun putProductList(productList: List<Product>): List<Product> {
        productDao.insertProductList(productList)
        return productList
    }
}