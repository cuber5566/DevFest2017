package com.cuber.devfest.ui.product.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.cuber.devfest.data.ProductRepository
import com.cuber.devfest.data.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductListPresenter(
        private var view: ProductListContract.View,
        private var productRepository: ProductRepository = ProductRepository.getInstance(),
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ProductListContract.Presenter, LifecycleObserver {

    lateinit var categoryId: String

    override fun setupParams(categoryId: String) {
        this.categoryId = categoryId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onSubscribe() {
        getProductList(categoryId)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onUnSubscribe() {
        compositeDisposable.clear()
    }

    override fun getProductList(categoryId: String) {
        compositeDisposable.add(productRepository.getProductListByCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { productList: List<Product> -> view.onGetProductList(productList) },
                        { throwable: Throwable -> view.onGetProductListError(throwable) }
                ))
    }
}