package com.cuber.devfest.data.source.remote

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.ProductSource
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.dao.ProductDao
import com.cuber.devfest.data.source.remote.service.ProductService
import com.cuber.devfest.data.source.remote.service.ServiceProvider
import com.cuber.devfest.util.scheduler.SchedulerProvider
import com.cuber.devfest.util.scheduler.AppScheduler
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

//        var products = ArrayList<Product>()
//        products.add(Product("01", "DevFest01" ,"Android Architecture Component01", 1))
//        products.add(Product("02", "DevFest02" ,"Android Architecture Component02", 2))
//        products.add(Product("03", "DevFest03" ,"Android Architecture Component03", 3))
//        products.add(Product("04", "DevFest04" ,"Android Architecture Component04", 4))
//        products.add(Product("05", "DevFest05" ,"Android Architecture Component05", 5))
//        products.add(Product("06", "DevFest06" ,"Android Architecture Component06", 6))
//
//        return Single.just(products)

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