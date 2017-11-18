package com.cuber.devfest.data

import com.cuber.devfest.R
import com.cuber.devfest.data.model.Product
import com.cuber.devfest.data.source.CartSource
import com.cuber.devfest.data.source.ProductSource
import com.cuber.devfest.util.PreferencesKey
import com.cuber.devfest.util.PreferencesTool
import com.cuber.devfest.util.ResourceTool
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ProductRepository(
        private var preferencesTool: PreferencesTool = PreferencesTool.getInstance(),
        private var resourceTool: ResourceTool = ResourceTool.getInstance()
) : ProductSource, CartSource {

    override fun getProductById(productId: String): Single<Product> {
        return Single.just(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))

    }

    override fun getProductListBySeller(sellerId: String): Single<List<Product>> {
        TODO("Call API")
    }

    override fun getProductListByCategory(categoryId: String): Single<List<Product>> {
        return Single.just(getFakeProductList()).delay(API_DELAY, TimeUnit.MILLISECONDS)
    }

    override fun getCartPostList(): List<Product> {
        return preferencesTool[PreferencesKey.CART_PRODUCT_LIST, object : TypeToken<List<Product>>() {
        }.type]
    }

    override fun isAddedCart(productId: String): Boolean = getCartPostList().any { it.id == productId }

    override fun addToCart(productId: String) {
    }

    override fun removeFromCart(productId: String) {
    }

    companion object {

        private var INSTANCE: ProductRepository? = null
        private var API_DELAY = 3000L

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

    private fun getFakeProductList(): List<Product> {
        val productList = ArrayList<Product>()
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))
        productList.add(Product(resourceTool.getString(R.string.test_product_id),
                resourceTool.getString(R.string.test_product_name),
                resourceTool.getString(R.string.test_product_content),
                5566))

        return productList
    }
}