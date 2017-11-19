package com.cuber.devfest.data

import com.cuber.devfest.R
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.api.ApiSource
import com.cuber.devfest.data.source.database.DatabaseSource
import com.cuber.devfest.data.source.preference.PreferencesSource
import com.cuber.devfest.data.source.resource.ResourceSource
import io.reactivex.Single

class ProductRepository(
        private var preferencesSource: PreferencesSource = PreferencesSource.getInstance(),
        private var resourceSource: ResourceSource = ResourceSource.getInstance(),
        private var apiSource: ApiSource = ApiSource.getInstance(),
        private var databaseSource: DatabaseSource = DatabaseSource.getInstance()
) : ProductRepo {

    override fun getProductById(productId: String): Single<Product> {
        return Single.just(Product(resourceSource.getString(R.string.test_product_id),
                resourceSource.getString(R.string.test_product_name),
                resourceSource.getString(R.string.test_product_content),
                5566))
    }

    override fun getProductListBySeller(sellerId: String): Single<List<Product>> {
        TODO("Call API")
    }

    override fun getProductListByCategory(categoryId: String): Single<List<Product>> {
        TODO("Call API")
    }

    override fun getCartPostList(): List<Product> {
        return databaseSource.db.productDao().loadAllUsers()
    }

    override fun isAddedCart(productId: String): Boolean {
        return databaseSource.db.productDao().loadProductById(productId) != null
    }

    override fun addToCart(product: Product) {
        databaseSource.db.productDao().insertProduct(product)
    }

    override fun removeFromCart(product: Product) {
        databaseSource.db.productDao().deleteProduct(product)
    }

    companion object {

        private var INSTANCE: ProductRepository? = null

        @JvmStatic
        fun getInstance(): ProductRepository {
            return INSTANCE ?: ProductRepository()
                    .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}