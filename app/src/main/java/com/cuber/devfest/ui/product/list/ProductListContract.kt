package com.cuber.devfest.ui.product.list

import com.cuber.devfest.data.model.Product

interface ProductListContract {

    interface View {

        fun onGetProductList(productList: List<Product>)

        fun onGetProductListError(throwable: Throwable)
    }

    interface Presenter{

        fun setupParams(categoryId: String)

        fun onSubscribe()

        fun onUnSubscribe()

        fun getProductList(categoryId: String)
    }
}