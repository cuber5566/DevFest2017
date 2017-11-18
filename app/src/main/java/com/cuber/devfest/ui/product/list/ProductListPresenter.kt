package com.cuber.devfest.ui.product.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.cuber.devfest.data.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductListPresenter(
        private var view: ProductListContract.View,
        private var productRepository: ProductRepository = ProductRepository.getInstance(),
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ProductListContract.Presenter, LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onSubscribe() {
        TODO("do something when lifecycle owner ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onUnSubscribe() {
        compositeDisposable.clear()
        TODO("release something when lifecycle owner ON_STOP")
    }

    override fun getProductList(categoryId: String) {

        compositeDisposable.add(productRepository.getProductListByCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(view::onGetProductListError)
                .subscribe(view::onGetProductList))
    }
}