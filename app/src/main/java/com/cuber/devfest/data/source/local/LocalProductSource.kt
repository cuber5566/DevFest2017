package com.cuber.devfest.data.source.local

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.ProductRepositoryImp
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.local.dao.ProductDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocalProductSource(

        private var productDao: ProductDao

) : ProductRepositoryImp {

    override fun getProductById(productId: String): Single<Product> {
        return productDao.loadProductById(productId)
                .subscribeOn(Schedulers.io())
    }

    override fun getProductList(): Single<List<Product>> {
        return productDao.loadProductList()
                .subscribeOn(Schedulers.io())
    }

    companion object {

        private var INSTANCE: LocalProductSource? = null

        @JvmStatic
        fun getInstance(): LocalProductSource =
                INSTANCE ?: LocalProductSource(

                        DaoProvider.getInstance().productDao

                ).apply { INSTANCE = this }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}