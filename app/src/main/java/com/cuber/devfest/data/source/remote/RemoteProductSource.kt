package com.cuber.devfest.data.source.remote

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.ProductSource
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.dao.ProductDao
import com.cuber.devfest.data.source.remote.service.ProductService
import com.cuber.devfest.data.source.remote.service.ServiceProvider
import com.cuber.devfest.util.scheduler.AppScheduler
import com.cuber.devfest.util.scheduler.SchedulerProvider
import io.reactivex.Single

class RemoteProductSource(

        private var productDao: ProductDao,
        private var productService: ProductService,
        private var appScheduler: AppScheduler

) : BaseRemoteSource(), ProductSource {

    override fun getProductById(productId: String): Single<Product> {
        return productService.getProductById(productId)
                .subscribeOn(appScheduler.io())
                .map { isApiSuccess(it) }
                .map { it.product }
    }

    override fun getProductList(): Single<List<Product>> {
        return productService.getProductList()
                .subscribeOn(appScheduler.io())
                .map { isApiSuccess(it) }
                .map { it.productList }
                .map { saveProductList(it) }
    }

    private fun saveProductList(productList: List<Product>): List<Product> {
        productDao.insertProductList(productList)
        return productList
    }

    companion object {

        private var INSTANCE: RemoteProductSource? = null

        @JvmStatic
        fun getInstance(): RemoteProductSource =
                INSTANCE ?: RemoteProductSource(

                        DaoProvider.getInstance().productDao,
                        ServiceProvider.getInstance().productService,
                        SchedulerProvider.getInstance()

                ).apply { INSTANCE = this }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}