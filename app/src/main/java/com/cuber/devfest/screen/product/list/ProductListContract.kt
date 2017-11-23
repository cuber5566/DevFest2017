package com.cuber.devfest.screen.product.list

import com.cuber.devfest.data.model.Product

interface ProductListContract {

    interface View {

        fun onGetProductList(productList: List<Product>)

        fun onGetProductListError(throwable: Throwable)
    }

    interface Presenter{

        fun onSubscribe()

        fun onUnSubscribe()

        fun getProductList()
    }
}