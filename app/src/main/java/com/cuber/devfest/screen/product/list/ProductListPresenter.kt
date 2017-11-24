package com.cuber.devfest.screen.product.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.cuber.devfest.data.ProductRepository
import com.cuber.devfest.data.ProductSource
import com.cuber.devfest.data.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductListPresenter(

        private var view: ProductListContract.View,
        private var productRepository: ProductSource = ProductRepository.getInstance(),
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()

) : ProductListContract.Presenter, LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onSubscribe() {
        getProductList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onUnSubscribe() {
        compositeDisposable.clear()
    }

    override fun getProductList() {
        compositeDisposable.add(productRepository.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { productList: List<Product> -> view.onGetProductList(productList) },
                        { throwable: Throwable -> view.onGetProductListError(throwable) }
                ))
    }
}