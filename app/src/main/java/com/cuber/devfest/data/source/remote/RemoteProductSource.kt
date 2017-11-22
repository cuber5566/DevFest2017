package com.cuber.devfest.data.source.remote

import android.support.annotation.VisibleForTesting
import com.cuber.devfest.data.BaseRepository
import com.cuber.devfest.data.ProductRepositoryImp
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.local.ProductOffline
import com.cuber.devfest.data.source.local.dao.DaoProvider
import com.cuber.devfest.data.source.remote.service.ProductService
import com.cuber.devfest.data.source.remote.service.ServiceProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RemoteProductSource(

        private var productOffline: ProductOffline,
        private var productService: ProductService

) : BaseRepository(), ProductRepositoryImp {

    override fun getProductById(productId: String): Single<Product> {
        return productService.getProductById(productId)
                .subscribeOn(Schedulers.io())
                .map { isApiSuccess(it) }
                .map { it.product }
    }

    override fun getProductList(): Single<List<Product>> {
        return productService.getProductList()
                .subscribeOn(Schedulers.io())
                .map { isApiSuccess(it) }
                .map { it.productList }
                .map { productOffline.putProductList(it) }
    }

    companion object {

        private var INSTANCE: RemoteProductSource? = null

        @JvmStatic
        fun getInstance(): RemoteProductSource =
                INSTANCE ?: RemoteProductSource(

                        ProductOffline(DaoProvider.getInstance().productDao),
                        ServiceProvider.getInstance().productService

                ).apply { INSTANCE = this }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}