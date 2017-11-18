package com.cuber.devfest.ui.product.list

import com.cuber.devfest.data.model.Product
import com.cuber.devfest.ui.base.BasePresenter

interface ProductListContract {

    interface View {

        fun onGetProductList(productList: List<Product>)

        fun onGetProductListError(throwable: Throwable)
    }

    interface Presenter : BasePresenter {

        fun getProductList(categoryId: String)
    }
}