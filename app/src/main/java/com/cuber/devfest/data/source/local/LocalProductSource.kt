package com.cuber.devfest.data.source.local

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.ProductSource
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.dao.ProductDao
import com.cuber.devfest.util.scheduler.SchedulerProvider
import com.cuber.devfest.util.scheduler.AppScheduler
import io.reactivex.Single

class LocalProductSource(

        private var productDao: ProductDao,
        private var appScheduler: AppScheduler


) : ProductSource {

    override fun getProductById(productId: String): Single<Product> {
        return productDao.loadProductById(productId)
                .subscribeOn(appScheduler.io())
    }

    override fun getProductList(): Single<List<Product>> {
        return productDao.loadProductList()
                .subscribeOn(appScheduler.io())
    }

    companion object {

        private var INSTANCE: LocalProductSource? = null

        @JvmStatic
        fun getInstance(): LocalProductSource =
                INSTANCE ?: LocalProductSource(

                        DaoProvider.getInstance().productDao,
                        SchedulerProvider.getInstance()

                ).apply { INSTANCE = this }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}