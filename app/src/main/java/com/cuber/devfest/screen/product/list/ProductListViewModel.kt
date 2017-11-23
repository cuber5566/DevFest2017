package com.cuber.devfest.screen.product.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.cuber.devfest.data.ProductRepository
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.screen.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class ProductListViewModel(
        application: Application,
        private var productRepository: ProductRepository
) : AndroidViewModel(application) {

    // data
    var productList = MutableLiveData<List<Product>>()

    //event
    var error = SingleLiveEvent<Throwable>()

    fun updateProductList() {
        productRepository.getProductList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ productList.value = it },
                        { error.value = it })
    }
}